# 深入剖析一条JMS消息
一条消息有以下三个部分组成：

1.消息头

2.属性

3.消息的有效负载

`消息具有不同的类型，这些类型由他们的有效负载所定义，有效负载自身可能是非常结构化的，比如说StreamMessage和BytesMessage对象等，或者是相当非结构化的，比如TextMessage,ObjectMessage和MapMessage.`

消息能够携带重要的数据或者用于系统中的事件通知。在大多数情况下，消息能够同时作为通知和携带数据的工具。

* 消息头携带了和消息有关的元数据信息

* 他描述了消息有谁创建、何时创建、数据的有效长度等信息。
* 消息头还描述了消息的目的地的路由信息、消息如何被确认等另外一些信息。