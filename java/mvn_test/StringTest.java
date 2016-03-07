package mvn_test;

public class StringTest {

	public static void main(String[] args) {
		test2();
	}

	public static void test1() {
		String parameter = "loglevel=notice;lua-time-limit=5000;latency-monitor-threshold=0;save=900=1;save=300=10;save=60=10000;tcp-keepalive=0;activerehashing=yes;pidfile=/var/run/redis.pid;appendonly=no;auto-aof-rewrite-percentage=100;hash-max-ziplist-entries=512;set-max-intset-entries=512;hll-sparse-max-bytes=3000;client-output-buffer-limit=normal=0=0=0;client-output-buffer-limit=slave=256mb=64mb=60;rdbcompression=yes;zset-max-ziplist-entries=128;hash-max-ziplist-value=64;appendfsync=everysec;client-output-buffer-limit=pubsub=32mb=8mb=60;repl-disable-tcp-nodelay=no;auto-aof-rewrite-min-size=64mb;slowlog-log-slower-than=10000;dbfilename=dump.rdb;port=6379;aof-rewrite-incremental-fsync=yes;tcp-backlog=511;appendfilename=\"appendonly.\";stop-writes-on-bgsave-error=yes;dir=./;hz=10;slave-priority=100;daemonize=yes;logfile=\"\";slave-serve-stale-data=yes;no-appendfsync-on-rewrite=no;list-max-ziplist-value=64;zset-max-ziplist-value=64;list-max-ziplist-entries=512;rdbchecksum=yes;databases=16;slave-read-only=yes;slowlog-max-len=128;timeout=0;maxmemory=80mb;requirepass=123456;masterauth=123456;";
		int pos = parameter.indexOf("requirepass=");
		if (pos >= 0) {
			int end = parameter.indexOf(";", pos);
			String password = parameter.substring(pos+"requirepass=".length(), end);
			System.out.println(password);
		}
	}
	
	public static void test2() {
		String str = "";
		String[] arr = str.split(",");
		System.out.println(arr.length);
	}
}
