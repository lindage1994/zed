###记一次同步方法事务引发的爆炸

话不多说，直接上代码

```
@Transactional(rollbackFor = Exception.class)
public synchronized JSONObject luckyDraw(String userName,String refurl)throws Exception{
....
}
```
当并发请求过来的时候，后面的请求开启事务，然后等待，不一会儿数据库连接池的连接数就被拿光了，连接池就泄露了

处理方法：在调用的地方加一个单线程的线程池（这个不是最佳的解决方案）

`private static ExecutorService singleThreadExecutor =  Executors.newSingleThreadExecutor();`