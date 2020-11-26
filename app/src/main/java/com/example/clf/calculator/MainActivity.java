package com.example.clf.calculator;

        import android.content.Intent;
        import android.content.res.Configuration;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text,text1,text2;//三个文本域
    double num1, num2;//操作数
    double sum;//运算结果
    StringBuffer temp = new StringBuffer();//输入字串
    char op;//操作符
    boolean mcount;//累加器计数标志
    boolean num2sign;//运算符标志（表示准备好输入第二个数）
    boolean comeout;//运算结果标志
    boolean Mnum;//累加器取数标志
    boolean num1alive;//操作数标志
    boolean num1is_ngt,num2is_ngt;//操作数正负标志
    boolean decimal;//小数点标志
    String num;
    String current1 = null;


    //初始化全局变量并设置监听器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);//输入文本域
        text1 = (TextView) findViewById(R.id.textView1);//历史文本域一
        text2 = (TextView) findViewById(R.id.textView2);//历史文本域
        num1 = 0;//计数值1
        num2 = 0;//计数值2
        num2sign = false;//新计数值
        decimal = false;//小数点标记
        num1is_ngt=false;//判断第一个数的正负
        num2is_ngt=false;//第二个数的正负
        num1alive=false;//计数值一是否存在
        comeout=false;//当前是否显示计算结果
        mcount=false;//是否有累加
        Mnum=false;//是否取计数值
        sum=0;
        op=' ';
        Button btn0 = (Button) findViewById(R.id.button0);
        btn0.setOnClickListener(this);
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button) findViewById(R.id.button5);
        btn5.setOnClickListener(this);
        Button btn6 = (Button) findViewById(R.id.button6);
        btn6.setOnClickListener(this);
        Button btn7 = (Button) findViewById(R.id.button7);
        btn7.setOnClickListener(this);
        Button btn8 = (Button) findViewById(R.id.button8);
        btn8.setOnClickListener(this);
        Button btn9 = (Button) findViewById(R.id.button9);
        btn9.setOnClickListener(this);
        Button btnpoint = (Button) findViewById(R.id.buttonpoint);
        btnpoint.setOnClickListener(this);
        Button btnadd = (Button) findViewById(R.id.buttonadd);
        btnadd.setOnClickListener(this);
        Button btnsub = (Button) findViewById(R.id.buttonsub);
        btnsub.setOnClickListener(this);
        Button btnmlt = (Button) findViewById(R.id.buttonmlt);
        btnmlt.setOnClickListener(this);
        Button btndev = (Button) findViewById(R.id.buttondev);
        btndev.setOnClickListener(this);
        Button btnans = (Button) findViewById(R.id.buttonans);
        btnans.setOnClickListener(this);
        Button btnsqrt = (Button) findViewById(R.id.buttonsqrt);
        btnsqrt.setOnClickListener(this);
        Button btndel = (Button) findViewById(R.id.buttondel);
        btndel.setOnClickListener(this);
        Button btnc = (Button) findViewById(R.id.buttonc);
        btnc.setOnClickListener(this);
        Button bttan = (Button) findViewById(R.id.bttan);
        bttan.setOnClickListener(this);
        Button btsin = (Button) findViewById(R.id.btsin);
        btsin.setOnClickListener(this);
        Button btcos = (Button) findViewById(R.id.btcos);
        btcos.setOnClickListener(this);
        Button btconmul = (Button) findViewById(R.id.btconmul);
        btconmul.setOnClickListener(this);
        Button btsquare = (Button) findViewById(R.id.btsquare);
        btsquare.setOnClickListener(this);

    }

    //实现所有按钮事件
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button0:
                save('0');
                break;
            case R.id.button1:
                save('1');
                break;
            case R.id.button2:
                save('2');
                break;
            case R.id.button3:
                save('3');
                break;
            case R.id.button4:
                save('4');
                break;
            case R.id.button5:
                save('5');
                break;
            case R.id.button6:
                save('6');
                break;
            case R.id.button7:
                save('7');
                break;
            case R.id.button8:
                save('8');
                break;
            case R.id.button9:
                save('9');
                break;
            case R.id.buttonmlt:
                opchar('*');
                break;
            case R.id.buttonsub:
                opchar('-');
                break;
            case R.id.buttonadd:
                opchar('+');
                break;
            case R.id.buttondev:
                opchar('÷');
                break;
            case R.id.buttonpoint:
                if(!decimal) {
                    decimal=true;
                    save('.');
                }

                break;

            case R.id.buttonc:
                start();
                text.setText("");
                text1.setText("");
                text2.setText("");
                temp.delete(0,temp.length());
                break;
            case R.id.buttonsqrt:
                caulsqrt();
                break;
            case R.id.btsin:
                Cal("sin");
                break;
            case R.id.bttan:
                Cal("tan");
                break;
            case R.id.btcos:
                Cal("cos");
                break;
            case R.id.btconmul:
                Cal("conmul");
                break;
            case R.id.btsquare:
                Cal("square");
                break;
            case R.id.buttondel:
                back();
                break;
            case R.id.buttonans:
                result();
                break;

        }
    }

    //回退键
    public void back() {
        if(comeout){
            start();
            text.setText("");
        }else if(temp.length()>0 && !num2sign ) {
            temp.deleteCharAt(temp.length() - 1);
            text.setText(temp.toString());
        }
    }
    //累加器
    public void M(char o) {
        switch (o){
            case '+':
                if(comeout){
                    sum+=num1;
                    mcount=true;
                }
                else if(temp.length()>0 && !num2sign){
                    sum+=Double.parseDouble(temp.toString());
                    mcount=true;
                    Toast.makeText(MainActivity.this, "M+", Toast.LENGTH_SHORT).show();
                }

                break;
            case '-':
                if(comeout) {
                    sum -= num1;
                    mcount = true;
                }
                else  if (temp.length() > 0 && !num2sign) {
                    sum -= Double.parseDouble(temp.toString());
                    mcount = true;
                    Toast.makeText(MainActivity.this, "M-", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //开根号
    public void caulsqrt() {
        if(comeout) {
            if(num1<0)
            {
                Toast.makeText(MainActivity.this, "负数不能开平方根", Toast.LENGTH_SHORT).show();

            }else {
                num1 = Math.sqrt(num1);
                String rs=String.format("%.6f",num1);//规避极小误差

                rs=rs.replaceAll("0+?$","");//去掉多余的0

                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉

                num=rs;
                text.setText(String.valueOf(num));
            }
        }else if(temp.length()>0){
            double current;
            current=Double.parseDouble(temp.toString());
            if(current>=0) {
                current = Math.sqrt(current);
                String rs=String.format("%.6f",current);//规避极小误差

                rs=rs.replaceAll("0+?$","");//去掉多余的0

                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉

                current1=rs;
                int len = temp.length();
                temp.delete(0, len);
                temp.append(String.valueOf(current1));
                text.setText(temp.toString());
            }
            else{
                Toast.makeText(MainActivity.this, "负数不能开平方根", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //初始化
    public void start() {
        num1 = 0;
        num2 = 0;
        num1alive=false;
        num2sign = false;
        decimal = false;
        num1is_ngt=false;
        num2is_ngt=false;
        comeout=false;
        op=' ';
        int len = temp.length();
        temp.delete(0, len);//在temp中从第0个字符开始删除
    }
    //输入操作数
    public  void save(char num) {
        if(num2sign) {
            if(!comeout)
                num1 = Double.parseDouble(temp.toString());
            comeout=false;
            num1alive = true;
            text2.setText(String.valueOf(num1));
            text1.setText(String.valueOf(op));
            int len = temp.length();
            temp.delete(0, len);
            num2sign = false;
            decimal=false;
            if (num2is_ngt) temp.append('-');
            temp.append(num);
            text.setText(temp.toString());
        }else {
            if(num1is_ngt)
            {
                temp.append('-');
                num1is_ngt=false;
            }
            if(comeout){
                int len = temp.length();
                temp.delete(0, len);
                comeout=false;
            }
            if(Mnum){
                int len = temp.length();
                temp.delete(0, len);
                Mnum=false;temp.append(num);
                text.setText(temp.toString());
            }
            temp.append(num);
            text.setText(temp.toString());
        }
    }
    //输入运算符
    public void opchar(char a) {
       if(num1alive)
        {
            result();
            num1alive=false;
        }
        int len=temp.length();
        if (len>0) {
            if (a == '-') {
                if (op == '*'||op =='÷'||op=='-'||op =='+')
                    num2is_ngt = true;
                else{
                    op = a;
                    num2is_ngt=false;
                }
            }
            else{
                op = a;
                num2is_ngt = false;
            }
            if(comeout)text1.setText(String.valueOf(num1));
            else text1.setText(temp.toString());
            text.setText(String.valueOf(op));
            if(num2is_ngt)text.append("-");
            num2sign=true;
        }
        else{
            if(a=='-'){
                if(!num1is_ngt) {
                    num1is_ngt = true;
                    text.setText(String.valueOf("-"));
                }else {
                    num1is_ngt=false;
                    text.setText("");
                }
            }
            else {
                num1is_ngt = false;
                text.setText(String.valueOf(""));
            }
        }
    }
    //计算运算结果
    public void result() {
        if (num1alive) {
            num2 = Double.parseDouble(temp.toString());
            text1.setText(String.valueOf(op));
            text1.append(String.valueOf(num2));
            if (op == '÷' && num2 == 0)
                Toast.makeText(MainActivity.this, "除数不能为0！", Toast.LENGTH_SHORT).show();
            else {
                comeout=true;
                num1alive=false;
                decimal=false;
                switch (op) {
                    case '+':
                        num1 = num1 + num2;
                        break;
                    case '-':
                        num1 = num1 - num2;
                        break;
                    case '*':
                        num1 = num1 * num2;
                        break;
                    case '÷':
                        num1 = num1 / num2;
                        break;
                }
                text.setText("=");
                String rs=String.format("%.6f",num1);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                num=rs;
                text.append(String.valueOf(num));
                op=' ';
            }
        }
    }
    public void Cal(String x) {
        if(comeout) {
            if(x.equals("sin")){
                num1 = Math.sin(num1*Math.PI/180);
                String rs=String.format("%.6f",num1);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                num=rs;
            }
            else if(x.equals("tan")){
                num1=Math.tan(num1*Math.PI/180);
                String rs=String.format("%.6f",num1);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                num=rs;
            }
            else if(x.equals("cos")){
                num1=Math.cos(num1*Math.PI/180);
                String rs=String.format("%.6f",num1);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                num=rs;
            }
            else if(x.equals("square")){
                num1=num1*num1;
                String rs=String.format("%.6f",num1);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                num=rs;
            }
            if(x.equals("conmul")&&num1<0){
                Toast.makeText(MainActivity.this, "阶乘不能为负数！", Toast.LENGTH_SHORT).show();
            }
            else if(x.equals("conmul")){
                    int sum = 1;
                    for (int i = 1; i <= num1; i++) {
                        sum = sum * i;
                    }
                    num1 = sum;
                    if (num1 == 0) num1 = 0;
                String rs=String.format("%.6f",num1);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                num=rs;
            }
            else
                text.setText(String.valueOf(num));

        }
        else if(temp.length()>0){
            double current;
            current=Double.parseDouble(temp.toString());
            if(x.equals("sin")){
                current = Math.sin(current*Math.PI/180);
                String rs=String.format("%.6f",current);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                current1=rs;
            }
            else if(x.equals("tan")){
                current=Math.tan(current*Math.PI/180);
                String rs=String.format("%.6f",current);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                current1=rs;
            }
            else if(x.equals("cos")){
                current=Math.cos(current*Math.PI/180);
                String rs=String.format("%.6f",current);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                current1=rs;
            }
            else if(x.equals("square")){
                current=current*current;
                String rs=String.format("%.6f",current);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                current1=rs;
            }
            else if(x.equals("conmul")&&current<0){
                Toast.makeText(MainActivity.this, "阶乘不能为负数！", Toast.LENGTH_SHORT).show();
            }
            else if(x.equals("conmul")){
                int sum=1;
                for(int i=1;i<=current;i++){
                    sum=sum*i;
                }
                current=sum;
                if(current==0)current=0;
                String rs=String.format("%.6f",current);//规避极小误差
                rs=rs.replaceAll("0+?$","");//去掉多余的0
                rs=rs.replaceAll("[.]$","");//如最后一位是.，则去掉
                current1=rs;
            }

            //current=Math.sin(current);//current = Math.sqrt(current);
            int len = temp.length();
            temp.delete(0, len);
            temp.append(String.valueOf(current1));
            text.setText(temp.toString());
        }
    }



}

