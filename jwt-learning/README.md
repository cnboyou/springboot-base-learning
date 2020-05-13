# 前言
针对前后端分离的项目，大多是通过 token 进行身份认证来进行交互，今天将介绍一种简单的创建 token 的方式 -- JWT。
  
## 基本介绍
### 2.1 定义
JSON Web Token（JWT）是一个非常轻巧的规范。这个规范允许我们使用 JWT 在用户和服务器之间传递安全可靠的信息。
  
### 2.2 组成部分
一个 JWT 实际上就是一个字符串，它由三部分组成，头部、载荷与签名。前两部分需要经过 Base64 编码，后一部分通过前两部分 Base64 编码后再加密而成。
  
如果读者不理解上边的陈述，不要紧，下文会详细讲解。

#### 头部（Header）

头部用于描述关于该 JWT 的最基本的信息，例如其类型以及签名所用的算法等，也可以被表示成一个 JSON 对象。例如：
```
{"typ":"JWT","alg":"HS256"}
```
在头部指明了签名算法是 HS256 算法。

经过 Base64 编码得到：eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9（第一部分）。下文参考资料提供线上加密/解密网址，感兴趣的读者可以自己尝试。

#### 载荷（playload）

载荷就是存放有效信息的地方。这些有效信息包含三个部分：

（1）标准中注册的声明（建议但不强制使用）
```
iss: jwt签发者
sub: jwt所面向的用户
aud: 接收jwt的一方
exp: jwt的过期时间，这个过期时间必须要大于签发时间
nbf: 定义在什么时间之前，该jwt都是不可用的.
iat: jwt的签发时间
jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
```
（2）公共的声明
```
公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息，
但不建议添加敏感信息，因为该部分在客户端可解密。
```
例如：
```
{"id":"123456","name":"MoonlightL","sex":"male"}
```
将该 json 字符串进行 Base64 编码得到：eyJpZCI6IjEyMzQ1NiIsIm5hbWUiOiJNb29ubGlnaHRMIiwic2V4IjoibWFsZSJ9（第二部分）。

（3）私有的声明
```
私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64 
是对称解密的，意味着该部分信息可以归类为明文信息。
```
注意：载荷中的这3个声明并不是都要同时设置。

签证（signature）

jwt的第三部分是一个签证信息。
```
这个部分需要 Base64 加密后的 header 和 Base64 加密后的 payload 使用 “.” 
连接组成的字符串，然后通过 header 中声明的加密方式进行加盐 secret 组合加密，
然后就构成了 jwt 的第三部分。
```
即将 eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEyMzQ1NiIsIm5hbWUiOiJNb29ubGlnaHRMIiwic2V4IjoibWFsZSJ9 进行 HS256 算法加密（header 定义的）得到：

e5dda3f17226c1c6ca7435cd17f83ec0c74d62bd8e8386e1a178cd970737f09f（第三部分）。

最后，我们将上述的 3 个部分的字符串通过 “.” 进行拼接得到 JWT:

eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEyMzQ1NiIsIm5hbWUiOiJNb29ubGlnaHRMIiwic2V4IjoibWFsZSJ9.e5dda3f17226c1c6ca7435cd17f83ec0c74d62bd8e8386e1a178cd970737f09f 。

为了验证上述生成的 jwt 是否合法，我们可以登录 JWT 官网(https://jwt.io)，官网界面提供 JWT 解密功能，将生成好的 JWT 复制到如下图中进行解密：

在实际开发中，用户登录成功后，后端生成 jwt 返回给前端，之后，前端与后端交互时携带 jwt 让后端验证 jwt 的合法性。