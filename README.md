最近在学值动画的时候，一直感觉学得差不多了，但自己感觉又下不了手，所以自己就从最简单的做起，写了个带数字的ProgressBar。

![simple.gif](https://github.com/1002326270xc/CircleProgress/blob/master/photo/simple.gif)

从图上面看，其实就几个部分组成的：最外层是一个圆，还有带进度的圆弧，中间带数字的进度。
###思路解析:
这里的进度条按照两类来走，第一类是按照图上第一个进度条来显示的，圆弧的颜色是一成不变的，而且颜色的比例也是按照进度来绘制的。第二类是按照图上第二个进度条来显示的，圆弧的颜色是通过一个颜色集合来不断地变动的，而且这个弧度的差值永远是90度，不断改变弧度的起点和终点，所以看到的效果就是弧度一直在圈上跑步一样。中间的数字就是由颜色的值动画和缩放动画组成的，所以图一和图二看到的数字一直有颜色的改变和缩放的效果，是不是经过这么一说，更一目了然了呢。
###使用：
 <pre><code>```
 <declare-styleable name="CircleProgressBarView">  
    <!--当前的进度-->
    <attr name="number_progress" format="integer" />   
    <!--外环的宽度-->
    <attr name="border_width" format="dimension" />
    <!--未达到进度的外环颜色-->
    <attr name="unreached_color" format="color" />  
    <!--达到进度的外环颜色-->
    <attr name="reached_color" format="color" />  
    <!--中间数字的颜色-->
    <attr name="number_corlor" format="color" />  
    <!--中间数字的大小-->
    <attr name="number_size" format="dimension" />  
    <!--如果这里是true的话，外环就用颜色数组来绘制了-->  
    <attr name="isColorful" format="boolean" />
 </declare-styleable>```
 </code></pre>

属性也就定义了那么多，如果你觉得还有需要添加那些属性或是有些改动可以直接回复我。可以在xml文件中定义这些属性的值，你也可以通过代码来设置这些属性:
<pre><code>
/** 
*  给中间的数字进度设置颜色
* @param numberColor 
*/
public void setNumberColor(int numberColor) {  
   this.numberColor = numberColor;    
   numPaint.setColor(this.numberColor);  
   invalidate();
}
</code></pre>
<pre><code>
/**
 * 给中间的数字进度设置大小，这里还比较了先前设置的文字大小做了对比，如果大于之前设置的文本两倍，直接抛出异常，为了之前的控件测量有效
* @param numberColor 
*/
public void setCuNumScale(float numberSize) {  
   if (numberSize > 2 * this.numberSize) {       
       throw new RuntimeException("you do not set the number textSize twice as big as your before your setting");  
   }  
   this.numberSize = numberSize;    
   numPaint.setTextSize(this.numberSize);   
   invalidate();
}
</code></pre>
<pre><code>
public void setReachedColor(int reachedColor) {  
   this.reachedColor = reachedColor;    
   reachedPaint.setColor(this.reachedColor);  
   invalidate();
}

public void setProgress(int progress) {  
   if (this.progress != progress) {       
       this.progress = progress;     
       //这里每次进来的时候让那个中间数字进行放大一下        
       startNumAnimation();   
       if (listener != null) {      
           listener.onChange(this.progress);  
       }   
   }
}

public void setBorderWidth(float borderWidth) {   
   this.borderWidth = borderWidth;    
   unReachedPaint.setStrokeWidth(this.borderWidth);    
   reachedPaint.setStrokeWidth(this.borderWidth); 
   invalidate();
}

public void setUnReachedColor(int unReachedColor) {    
   this.unReachedColor = unReachedColor;    
   unReachedPaint.setColor(this.unReachedColor);  
   invalidate();
}

public void setNumberSize(float numberSize) {  
   this.numberSize = numberSize;    
   numPaint.setTextSize(this.numberSize);  
   invalidate();
}

public void setIsColorFul(boolean isColorFul) {  
   this.isColorFul = isColorFul;    
   invalidate();
}
</code></pre>

后面的几个方法就不做介绍了，通过看属性的定义也能知道是啥意思了。
###关于我:
   - email: a1002326270@163.com
   - 简书: http://www.jianshu.com/users/7b186b7247c1/latest_articles
