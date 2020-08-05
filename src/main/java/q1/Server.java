package q1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * 输入流对象,读取浏览器请求
     */
    private InputStream input;

    /**
     * 输出流对象，响应内容给浏览器
     */
    private OutputStream out;

    public void doRequest(int port) throws IOException {

       ServerSocket serverSocket = new ServerSocket(port);

       Socket socket = serverSocket.accept();

       input = socket.getInputStream();
       out = socket.getOutputStream();

        String read = read();
        if ("/shutdown".equals(read)){
            input.close();
            out.close();
            socket.close();
            return;
        }
        resposne(read);

    }

    /**
     * 读取文件，响应浏览器
     * @param filePath
     */
    private void resposne(String filePath) {

        File file = new File("f:" + filePath);
        System.out.println(file.getPath());

        if (file.exists()){

            try {

                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuffer buffer = new StringBuffer();
                String line;

                while((line=reader.readLine()) != null){
                    buffer.append(line).append("\r\n");
                }

                StringBuffer result = new StringBuffer();
                result.append("HTTP/1.1 200 ok \r\n");
                result.append("Content-Type:text/html \r\n");
                result.append("Content-Length:" + file.length() + "\r\n");
                result.append("\r\n:" + buffer.toString());

                out.write(result.toString().getBytes());

                out.flush();
                out.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            // 2、资源不存在，提示 file not found
            StringBuffer error = new StringBuffer();
            error.append("HTTP/1.1 404 not found \r\n");
            error.append("Content-Type:text/html \r\n");
            error.append("Content-Length:20 \r\n").append("\r\n");
            error.append("<h1 >404 Not Found..</h1>");
            try {
                out.write(error.toString().getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解析请求的资源路径
     * @example：GET /index.html HTTP/1.1
     * @return
     */
    public String read(){

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        try {
            String requestStr = reader.readLine();
            System.out.println(requestStr);
            String[] split = requestStr.split(" ");

            if (split.length != 3){
                return null;
            }
            System.out.println(split[1]);
            return split[1];

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.doRequest(8080);
    }
}
