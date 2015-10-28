package com.dan_nixon.csc3621.cw1.ex3;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;
import com.dan_nixon.csc3621.cw1.ex3.OTPAttack;

public class OTPAttackTest
{
  @Test
  public void testLoadCiphersFromFile() throws IOException
  {
    File c1 = new File(this.getClass().getResource("/ex3_cw_cipher_1.bin").getFile());
    File c2 = new File(this.getClass().getResource("/ex3_cw_cipher_2.bin").getFile());
    File c3 = new File(this.getClass().getResource("/ex3_cw_cipher_3.bin").getFile());
    File c4 = new File(this.getClass().getResource("/ex3_cw_cipher_4.bin").getFile());
    File c5 = new File(this.getClass().getResource("/ex3_cw_cipher_5.bin").getFile());
    File c6 = new File(this.getClass().getResource("/ex3_cw_cipher_6.bin").getFile());
    File c7 = new File(this.getClass().getResource("/ex3_cw_cipher_7.bin").getFile());

    File[] cipherFiles = {c1, c2, c3, c4, c5, c6, c7};
    byte[][] ciphers = OTPAttack.loadCiphersFromFiles(cipherFiles);

    // Test number of ciphers loaded
    assertEquals(7, ciphers.length);

    // Test some values
    assertEquals((byte) 0xDC, ciphers[0][0]);
    assertEquals((byte) 0xF7, ciphers[6][5]);
  }

  @Test
  public void testGetAnalysisStrings() throws IOException
  {
    File c1 = new File(this.getClass().getResource("/ex3_cw_cipher_1.bin").getFile());
    File c2 = new File(this.getClass().getResource("/ex3_cw_cipher_2.bin").getFile());
    File c3 = new File(this.getClass().getResource("/ex3_cw_cipher_3.bin").getFile());
    File c4 = new File(this.getClass().getResource("/ex3_cw_cipher_4.bin").getFile());
    File c5 = new File(this.getClass().getResource("/ex3_cw_cipher_5.bin").getFile());
    File c6 = new File(this.getClass().getResource("/ex3_cw_cipher_6.bin").getFile());
    File c7 = new File(this.getClass().getResource("/ex3_cw_cipher_7.bin").getFile());

    File[] cipherFiles = {c1, c2, c3, c4, c5, c6, c7};
    OTPAttack otpa = new OTPAttack(cipherFiles);

    File target = new File(this.getClass().getResource("/ex3_cw_cipher_target.bin").getFile());
    byte[] targetCipher = Utils.readBinaryFile(target);

    String[] analysisStrings = otpa.getAnalysisStrings(targetCipher);
    assertEquals(7, analysisStrings.length);
  }

  @Test
  public void testGuessPlainText() throws IOException
  {
    File c1 = new File(this.getClass().getResource("/ex3_cw_cipher_1.bin").getFile());
    File c2 = new File(this.getClass().getResource("/ex3_cw_cipher_2.bin").getFile());
    File c3 = new File(this.getClass().getResource("/ex3_cw_cipher_3.bin").getFile());
    File c4 = new File(this.getClass().getResource("/ex3_cw_cipher_4.bin").getFile());
    File c5 = new File(this.getClass().getResource("/ex3_cw_cipher_5.bin").getFile());
    File c6 = new File(this.getClass().getResource("/ex3_cw_cipher_6.bin").getFile());
    File c7 = new File(this.getClass().getResource("/ex3_cw_cipher_7.bin").getFile());

    File[] cipherFiles = {c1, c2, c3, c4, c5, c6, c7};
    OTPAttack otpa = new OTPAttack(cipherFiles);

    File target = new File(this.getClass().getResource("/ex3_cw_cipher_target.bin").getFile());
    byte[] targetCipher = Utils.readBinaryFile(target);

    String[] analysisStrings = otpa.getAnalysisStrings(targetCipher);
    String messageGuess = otpa.guessPlainTextFromAnalysis(analysisStrings, targetCipher.length);

    assertEquals("-ou hav- d-n- your w-rk", messageGuess);
  }
}
