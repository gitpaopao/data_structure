package io;

import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends InputStream {

    private InputStream is = null;

    public DecryptInputStream(InputStream is) {
        this.is = is;
    }

    @Override
    public int read() throws IOException {
        int a = this.is.read();
        if (a != -1){
            a-=2;
            if (a<(97)){
                a+=26;
            }
        }
        return a;
    }
}
