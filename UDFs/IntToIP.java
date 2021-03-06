/*
> export CLASSPATH=/home/vshue/svn/ds/aws/hiveudf/hadoop-core-0.20.2.jar:/home/vshue/svn/ds/aws/hiveudf/hive-exec-0.8.0.jar
> javac IntToIP.java
> jar cf IntToIP.jar IntToIP.class
*/

package org.vangie.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;


@Description(
  name="IntToIPUDF",
  value="Converts an int input to IP string",
  extended="> SELECT inttoipudf(643366670) from foo limit 1;\n"+
          "'38.88.255.14'"
  )
class IntToIP extends UDF {
  
  public Text evaluate(Text input) {
    if(input == null) return null;
    
    int num = Integer.parseInt(input.toString());
    String d = ""+num%256;
    for (int i = 3; i > 0; i--)
    {
        num = (int)Math.floor(num/256);
        d = num%256 + "." + d;
    }
    return new Text(d);
  }
  
}
