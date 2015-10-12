# web-crawler
整个项目暂时由4部分组成
* web-crawler-core 
* web-crawler-fetcher
* web-crawler-net
* wen-crawler-pool
* 

### web-crawler-core
整个项目的核心，主要定义了爬虫类`Crawler`的一些特性并实现了部分方法，里面可以继承`Crawler`来实现不同抓取策略的爬虫类

### web-crawler-fetcher
这部分主要定义了抓取页面的线程`Fetcher`，并定义了访问页面的接口`Visitor`，用户需要自行实现`Visitor`接口并解析页面，获取用户需要的页面信息

### web-crawler-net
这部分主要对http请求`Request`和服务器的相应`Response`进行封装，并定义和实现代理`Proxy`相关的功能

### web-crawler-pool
这部分主要定义了url资源池相关的接口`UrlPool`，以及对URL试题进行封装的接口`PooledUrl`,并进行了简单的实现，用户可以采取不同的存储策略实现自己的url资源池
