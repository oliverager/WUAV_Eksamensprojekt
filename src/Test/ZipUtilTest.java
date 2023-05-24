package Test;

import GUI.Util.ZipUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ZipUtilTest {

    @Test
    void zip() throws IOException {
        new ZipUtil().zip(new File(""));
    }

}