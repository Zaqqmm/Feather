package top.zaqqmm.oss.feather.core.server;

/**
 * @author zaqqmm
 * @description: ${DESCRIPTION}
 * @create 2019-07-22 16:52
 */
public interface Server {

    void startServer() throws Exception;
    void shutDownServer() throws Exception;
    void joinServer() throws Exception;


}
