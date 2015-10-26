package com.dan_nixon.csc3621.cw1.ex3;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import com.dan_nixon.csc3621.cw1.Utils;
import com.dan_nixon.csc3621.cw1.ex3.OneTimePadGenerator;

public class OneTimePadApp
{
  public static void main(String[] args) throws IOException
  {
    Map<String, String> options = Utils.parseCommandLine(args);

    if (options.containsKey("generate-pad-file"))
    {
      int length = Integer.parseInt(options.get("length"));
      byte[] padData = null;
      if (options.containsKey("seed"))
      {
        int seed = Integer.parseInt(options.get("seed"));
        padData = OneTimePadGenerator.generateOneTimePad(seed, length);
      }
      else
      {
        padData = OneTimePadGenerator.generateOneTimePad(length);
      }

      File padFile = new File(options.get("generate-pad-file"));
      Utils.writeBinaryFile(padFile, padData);
    }
    else if (options.containsKey("pad-file"))
    {
      File padFile = new File(options.get("pad-file"));
      OneTimePadEncryption otpe = new OneTimePadEncryption(padFile);

      if (options.containsKey("encrypt"))
      {
        //TODO
      }
      else if (options.containsKey("decrypt"))
      {
        //TODO
      }
    }
  }
}
