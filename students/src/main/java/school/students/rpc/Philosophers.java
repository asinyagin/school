package school.students.rpc;

import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("Philosophers")
public interface Philosophers extends school.Philosophers {
}
