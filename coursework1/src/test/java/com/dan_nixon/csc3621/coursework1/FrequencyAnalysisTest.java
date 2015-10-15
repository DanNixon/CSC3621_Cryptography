package com.dan_nixon.csc3621.coursework1;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import com.dan_nixon.csc3621.coursework1.FrequencyCounter;
import com.dan_nixon.csc3621.coursework1.FrequencyAnalysis;

public class FrequencyAnalysisTest
{
  // @Test
  // public void testReadCourseworkFile() throws IOException
  // {
  //   File englishFile = new File(this.getClass().getResource("/pg1661.txt").getFile());
  //   File cipherFile = new File(this.getClass().getResource("/Exercise1Ciphertext.txt").getFile());

  //   FrequencyCounter efa = new FrequencyCounter(englishFile);
  //   efa.count();

  //   double[] englishNorm = efa.normalise();

  //   FrequencyCounter fa = new FrequencyCounter(cipherFile);
  //   fa.count();

  //   assertTrue(fa.occurrences('e') > 1);

  //   System.out.println(fa);
  //   System.out.println(fa.totalCount());

  //   double[] norm = fa.normalise();
  //   System.out.println(Arrays.toString(norm));

  //   int[] mapping = fa.decipher(englishNorm);
  //   System.out.println(Arrays.toString(mapping));

  //   for (int i = 0; i < 26; i++)
  //   {
  //     StringBuilder sb = new StringBuilder();
  //     // sb.append(Utils.getCharFromIndex(i));
  //     sb.append("(");
  //     sb.append(norm[i]);
  //     sb.append(") => ");
  //     // sb.append(Utils.getCharFromIndex(mapping[i]));
  //     sb.append("(");
  //     // sb.append(FrequencyCounter.EXPECTED_RELATIVE_FREQ[mapping[i]]);
  //     sb.append(englishNorm[i]);
  //     sb.append(")");
  //     System.out.println(sb.toString());
  //   }

  //   String remapped = fa.decipherAndRemap(englishNorm);
  //   System.out.println(remapped);
  // }
}
