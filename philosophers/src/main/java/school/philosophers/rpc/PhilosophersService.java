package school.philosophers.rpc;

import com.googlecode.jsonrpc4j.JsonRpcService;
import school.Philosophers;

@JsonRpcService("/api/Philosophers")
public interface PhilosophersService extends Philosophers {
}
