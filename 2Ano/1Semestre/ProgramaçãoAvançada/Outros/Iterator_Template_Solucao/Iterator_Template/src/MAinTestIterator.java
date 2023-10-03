import com.pa.adts.stack.Stack;
import com.pa.adts.stack.StackArray;

import java.util.*;


public class MAinTestIterator {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList();

        for (int i = 0; i < 20; i++)
            list.add(i);

        for (Integer e : list) {
            System.out.println(e);
        }

        Iterator<Integer> it = list.iterator();
        while(it.hasNext()) {
            Integer e = it.next();
            System.out.println(e);
        }

    }


}
