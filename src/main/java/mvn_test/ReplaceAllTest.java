package mvn_test;
/**
 * @author hzweizijun 
 * @date 2015年10月26日 下午6:45:48
 */
public class ReplaceAllTest {

	public static void main(String[] args) {
		String parameter = "loglevel=verbose;lua-time-limit=5000;latency-monitor-threshold=0;save=900=1;save=300=10;save=60=10000;tcp-keepalive=0;activerehashing=yes;pidfile=/var/run/redis.pid;appendonly=no;auto-aof-rewrite-percentage=100;hash-max-ziplist-entries=512;set-max-intset-entries=512;hll-sparse-max-bytes=3000;client-output-buffer-limit=normal=0=0=0;client-output-buffer-limit=slave=256mb=64mb=60;rdbcompression=yes;zset-max-ziplist-entries=128;hash-max-ziplist-value=64;appendfsync=everysec;client-output-buffer-limit=pubsub=32mb=8mb=60;repl-disable-tcp-nodelay=no;auto-aof-rewrite-min-size=64mb;slowlog-log-slower-than=10000;dbfilename=dump.rdb;port=6379;aof-rewrite-incremental-fsync=yes;tcp-backlog=511;appendfilename=\"appendonly.aof\";stop-writes-on-bgsave-error=yes;dir=./;hz=10;slave-priority=100;daemonize=yes;logfile=\"\";slave-serve-stale-data=yes;no-appendfsync-on-rewrite=no;rename-command=EVAL=EVAL64da0131-62c8-4e7a-b6ad-4d6daf6b20f6;rename-command=EVALSHA=EVALSHA7165db76-307e-461b-bc4b-087277abddc4;rename-command=SCRIPT=SCRIPTd8e1d8d8-d0ab-47c4-8a5c-233cded770c7;rename-command=BGREWRITEAOF=BGREWRITEAOF89562dec-fc27-4f45-a9c9-279b9a030806;rename-command=BGSAVE=BGSAVEd7c6aa9d-ffab-4914-a0bc-bd29091d6523;rename-command=CLIENT=CLIENT4dc87960-e7b4-425b-a502-1a642016ecb0;rename-command=CONFIG=CONFIGe3bc4924-5fdf-4858-81c4-bf8d37d4297c;rename-command=COMMAND=COMMAND390731a4-10b6-48d3-a63f-5c90355b453d;rename-command=DEBUG=DEBUG6eff4abe-34c9-47f9-8fce-511b0d515e44;rename-command=LASTSAVE=LASTSAVE534a1eaa-1518-4588-8ae6-c3ccad64bfe5;rename-command=MONITOR=MONITOR1ee28f28-aaab-41c3-acfd-f3c8b1a69b1f;rename-command=SAVE=SAVEc1b353b1-92a7-490f-8cbd-2530ad7448a6;rename-command=SHUTDOWN=SHUTDOWNe9f69b89-2f60-4a44-98c1-d6b3a60d0c01;rename-command=SLAVEOF=SLAVEOF889ec7e5-5dd0-436e-9d22-e08ddfc8568f;rename-command=SLOWLOG=SLOWLOG27d230df-ea7c-449b-bdd5-d43ffd60ee37;rename-command=SYNC=SYNCf2aed363-7ce8-4a35-9418-88ef64442c81;list-max-ziplist-value=64;zset-max-ziplist-value=64;list-max-ziplist-entries=512;rdbchecksum=yes;databases=16;slave-read-only=yes;slowlog-max-len=128;timeout=0;maxmemory=50mb;requirepass=123456;masterauth=123456;";
		parameter = parameter.replaceAll("rename-command=\\w+=[\\w|-]+;", "");
		System.out.println(parameter);
		
	}

}
