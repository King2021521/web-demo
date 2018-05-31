package com.nd.me.component.jexl;

import org.apache.commons.jexl3.*;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @Author
 * @Description
 * @Date Create in 下午 2:43 2018/5/14 0014
 */
public class JexlHandler {
    private static JexlEngine engine = new JexlBuilder().create();
    private static ExpressionParser parser = new SpelExpressionParser();

    public static void main(String[] args){
        testSpel();
        testJexl();
    }

    public static void testJexl(){
        long t1 = System.currentTimeMillis();
        JexlContext context = new MapContext();
        context.set("a",11);
        context.set("b",12);
        context.set("out",System.out);

        JexlExpression e = engine.createExpression("out.println(a+b+a*b+a/b)");

        Object result = e.evaluate(context);
        System.out.println("jexl耗时"+(System.currentTimeMillis()-t1)+"ms");
    }

    public static void testSpel(){
        long t1 = System.currentTimeMillis();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("a",11);
        context.setVariable("b",12);
        context.setVariable("out",System.out);

        Expression exp = parser.parseExpression("#out.println(#a+#b+#a*#b+#a/#b)");
        exp.getValue(context);
        System.out.println("spel耗时"+(System.currentTimeMillis()-t1)+"ms");
    }
}
