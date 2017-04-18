import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by emigore on 2017-03-16.
 */
@RunWith(Parameterized.class)
public class InfixExpressionEvaluatorTest {
    public String expression;
    public Double result;

    InfixExpressionEvaluator infixEvaluator = new InfixExpressionEvaluator();
    List<String> currentExpression;

    public InfixExpressionEvaluatorTest(String expression, Double result){
        this.expression = expression;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection equations(){
        return Arrays.asList(new Object[][] {
                {"2+2", 4.0},
                {"(2+2)*2", 8.0},
                {"6/(9+16)", 0.24},
                {"3*6+4/5", 18.8},
                {"2*(4*(5-9))",-32.0},
                {"12-((4+7)/(2-5))", 15.666666666666666},
                {"((6+2)*(3-1)/(2*(3+9)))",0.6666666666666666},
                {"(6-8)/((2+3)+6)",-0.18181818181818181818},
                {"2+(2*2+3)",9.0}
        });
    }

    @org.junit.Test
    public void evaluateTest() throws Exception {
        System.out.println("EXPRESSION: "+expression+", EXPECTED RESULT: "+ result);
           assertEquals(result, infixEvaluator.evaluateExpression(expression));
    }

}