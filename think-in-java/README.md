# 3.操作符

## 3.1 静态导入
```import static```
```java
import static java.lang.System.out;
out.println("Import static");
```

## 3.2 Java操作符
+ 基本类型几乎支持所有的操作符
+ ```=```、```==```、```!=```支持所有的对象 - ```判断是否是同一个引用地址```
+ String支持```+```和```+=```，表示字符串连接操作

## 3.3 优先级
+ 使用```括号```来明确指定运算的优先级

## 3.4 赋值
+ ```右值：```可以是任何常数、变量或者表达式，能生成值就行
+ ```左值：```必须是一个明确的、已命名的变量，即```有物理空间存储右边的值```
+ 对于基本类型赋值：修改一方不会改变另外一方的值
+ 对于对象类型：由于赋值的是```引用```，修改一方会影响到另外一方
+ 在```方法调用```中，如果传递给方法的是一个对象，也会修改对象的值，如果是基本类型，不会被修改

## 3.5 算术操作符
+ ```java.util.Random.nextInt(bound)```，才有边界，nextFloat其他的都没有
+ ```Random(seedUniquifier() ^ System.nanoTime())```：默认使用当前时间作为"种子"
+ 对于```Random```，种子一样，生成的数字也一样，伪随机
+ 一元加不会提升```char```类型，但是一元减会提升```char```为```int```

## 3.6 自增和自减操作符
+ 注意前缀和后缀的区别：先运算再取值和先取值后操作

## 3.7 关系操作符
+ 生产```boolean```结果
+ 操作符：```<```、```>```、```<=```、```>=```、```==```、```!=```
+ ```==```、```!=```适合所有基本数据类型，但是boolean不适合其他比较符号

## 3.8 逻辑操作符
+ 生产```boolean```结果
+ 操作符：```&&```、```||```、```!```
+ 只能操作```boolean```，不能像C那样把```int```当做```boolean```
+ ````短路：````一旦能够明确无误地确定整个表达式的值，就不再计算表达式剩余的部分

## 3.9 直接常量
+ 16进制以```0x```或```0X```开始
+ 8进制有一个前缀```0```
+ 从```Java7```开始，二进制数使用```0b```或```0B```开始，或者使用下划线方式```1_000_000```表示一百万，对应的二进制表示```0b1111_0100_0010_0100_0000```

## 3.10 按位操作符
+ ```下面针对的是位 - bit```
+ 1 & 1 = 1
+ 1 | 0 = 1 
+ 0 | 0 = 0
+ 1 ^ 0 = 1
+ 1 ^ 1 = 0
+ ~1 = 0
+ ~0 = 1
+ ```实际应用意义```：权限设计，读写删除等

## 3.11 移位操作符
+ 只适用于```整数```类型，如int、long
+ 如果操作char、byte或short，会自动转为int处理
+ ```<<```：10 << 2 --> 1000
+ ```>>```：1000 >> 2 --> 1 - 带符号右移
    + ```正数```：左边加0
    + ```负数```：左边加1
+ ```>>>```：无符号右移
    + ```不管正负数```：左边都是加0
+ https://segmentfault.com/q/1010000000414831
+ 对于int类型，只有数值(右操作数)右端的的低5位才有用，这样可以防止移位超过int值所具有的位数
+ 因为2的5次方是32,而int型值只有32位
+ 同样的，long类型，只有数值右端的的低6位才有用

## 3.12 三元操作符
+ ```表达式 ? 值1 : 值2```

## 3.13 字符串操作
+ ```+```和```+=```

## 3.14 使用操作符容易犯的错误
+ ```不确定的时候，不使用括号```
+ ````while(x=y)，把赋值当做等于，对于boolean会出现问题````

## 3.15 类型转换操作符
+ Java有时候会自动进行类型转换
+ 有时候，需要程序员自己强制转换：```(目标类型)变量```
+ 不支持```boolean```的类型转换
+ ```浮点数```转```整数```，总是截取，不会舍入
+ 如果想舍入，需要借助 ```Math.round()```
+ 对```char,byte,short```执行位操作，会自动提升为```int```

## 3.16 Java没有sizeof



---

# 5.初始化与清理

## 5.1 用构造器确保初始化
+ 默认构造函数，如果自己实现了构造函数，Java不会自动提供默认构造函数了，需要自己手动写
+ 上面的问题，在开发中容易出现，为什么有时候反序列化失败，大部分原因就是没有无参的默认构造函数导致的
+ ```构造器没有返回值```，通过```javap -v```指令查看class，最后指令是```return```而不是```ireturn```

## 5.2 方法重载
+ 方法名相同(想一想构造函数)，参数类型不同
+ 返回值不同可以吗？想一想不可以，如果返回值是兼容的类型，编译器选择调用哪个会出现问题。
+ 编译器根据```参数类型列表```来判断调用的是哪个方法

## 5.3 默认构造函数
+ Java提供的无参构造函数，在创建一个对象的时候会调用默认构造函数
+ 如果重载有参构造函数，默认的构造函数则不会再提供

## 5.4 this
+ 除开```静态方法```，实例方法都会默认当前实例```this```作为该方法的第一个参数：
```java
class MyObj{
    void fun(){
        System.out.println("this demo");
    }
    void fun(int i){
        System.out.println("this demo");
    }
}
```
+ 通过反编译```javap -v```看到的局部变量表：
```bash
## void fun();
stack=2, locals=1, args_size=1
LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  this   Lcn/rumoss/study/Initialization02/MyObj;
## void fun(int);
stack=2, locals=2, args_size=2
LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  this   Lcn/rumoss/study/Initialization02/MyObj;
            0       9     1     i   I
## 构造函数
cn.rumoss.study.Initialization02.MyObj();
    descriptor: ()V
    flags:
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 16: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcn/rumoss/study/Initialization02/MyObj;
```
+ 同时有可以看到，默认的构造函数里面，也有this作为参数
+ this只能在非静态方法中使用
+ 需要返回当前对象时，才会要求显式调用```this```
+ 使用```this```在一个构造器中调用另外一个构造器：this(x,y,z...)，必须第一行调用，不能重复调用
+ ```static```：静态方法不能直接调用非静态方法(作为参数传入除外，此时实际是局部变量)，反之可以

## 5.5 清理：终结处理和垃圾回收
+ GC只会回收由```new```分配的空间
+ Java使用```finalize()```来帮助程序员写回收不是由```new```开辟的空间，而是由其他```special```的内存空间的代码
+ ```finalize()```在GC准备释放对象空间之前被调用，在下一次垃圾回收动作发生时才会真正回收对象占用的内存，因此可以用来在GC的时候执行一些重要的清理工作

注意下面三点：
+ (1) 对象可能不会被垃圾回收
+ (2) 垃圾回收不等同于```析构函数```，换句话说，需要自己去写方法做一些清理工作，垃圾回收发生时，调用```finalize```，如果没发生，也不会调用。另外垃圾回收也有部分开销的
+ (3) 垃圾回收只与内存有关。```finalize()```需求在一种情况适用：通过某种创建对象方式以外的方式为对象分配了存储空间，```本地方法```。```finalize()```可能需要在其中调用C的```free()```方法来释放这部分由```malloc()系列函数```分配的空间，避免造成内存泄漏

另外需注意：
+ 垃圾回收器的存在不能完全替代```析构```函数
+ 不能直接调用```finalize()```方法
+ 如果需要进行除释放空间之外的清理工作，还是得明确调用某个恰当的Java方法(等同于析构函数作用)
+ ```垃圾回收```和```终结操作```都不保证一定会被调用，除非JVM内存耗尽

如果使用```finalize()```方法：
+ ```终止条件```：在```finalize()```中检查某个是否清理的标识，如果对象被GC回收，触发```finalize()```，发现某个条件不满足，可以抛出提示

垃圾回收算法：
+ ```引用计数：```简单但是慢，出现循环引用-对象应该被回收，但是引用不为零
+ ```引用溯源：```对任何活的对象，追溯到其存活的堆栈或静态存储区之中的引用，遍历所有的引用，找到所有的引用，解决循环引用的问题
+ ```停止复制：```暂停程序的运行，不属于后台回收模式，将所有存活的对象从当前堆复制到另外一个堆
    + 缺点一：效率低，需要维护比实际多一倍的空间
    + 缺点二：无效复制，当应用稳定产生很少垃圾时，不应该复制移动。解决这个问题 - ```自适应```，检测到无新垃圾，就会切换到另外一种工作模式
    
自适应算法：
+ ```标记-清除：```速度慢，但当少量垃圾回收时会很快
    + 从堆栈和静态存储区出发，遍历所有引用，进而找出所有存活的对象
    + 每当找到一个存活对象，给对象设置一个标识，该过程不会回收任何对象
    + 只有全部标记工作完成才会开始清理工作
    + 缺点：剩下的是不连续的堆空间，需要重新整理剩下的对象 - 也需要暂停程序 STW
    
内存块：内存分配以较大的块为单位，如果对象较大，会占用单独的块
+ ```自适应：```
    + 垃圾回收器在回收的时候，可以往废弃的块里拷贝对象，每块有```代数```记录其是否还存活
    + 如果块在某处被引用，代数会增加
    + 垃圾回收器将对上次回收动作之后新分配的块进行整理 - 处理短命临时对象很有帮助
    + 垃圾回收器会定期进行完整的清理动作 - 大型对象仍然不会被复制，只是其代数会增加，只有小型对象的块被复制并整理
    + 对象稳定，垃圾回收效率低 -> ```标记-清除```
    + 对空间碎片多 -> ```停止-复制```

一句话总结：```自适应的、分代的、停止-复制、标记-清除```式垃圾回收器

## 5.6 成员初始化

+ 局部变量需要初始化才能使用，否则编译器会提示错误
+ char的默认初始化值是```0```，打印出来是空格

两种方式指定初始化：
+ 定义成员变量的时候初始化
+ 构造函数中初始化
+ 可以调用成员方法给成员变量提供初始值
+ 初始化时，不能像JS中那样向前引用值
+ 缺点：缺少灵活性

## 5.7 构造器初始化

+ 自动初始化在构造器调用之前发生
+ 编译器不会强制你在构造器或者其他地方初始化成员变量的原因是，这些变量在定义的时候已经被自动初始化过
+ 变量的初始化在任何方法调用之前，包括构造函数
+ 初始化顺序：
    + 变量在类中定义的顺序决定了初始化的顺序

静态数据初始化：
+ ```static```不能修饰局部变量，即使是静态方法中也不可以
+ 初始化顺序：先```静态对象```，然后再```非静态对象(实例对象)```
+ 构造器可以看作静态方法
+ 静态初始化只在```Class```对象首次加载的时候进行一次
+ 通过```javap -v```测试发现，```static块```之外的变量和之前的变量都在一块的，只是把块内的变量当做局部变量，块外的变量作为静态的全局变量了，执行顺序按照位置执行

实例数据初始化：
+ 通过```javap -v```测试发现，非实例代码块会合并与构造函数之中，且在构造函数之前执行

## 5.8 数组初始化

+ 固有的成员 - ```length```
+ 数组能在运行时创建，不需要提前初始化大小
+ 可变参数列表实质就是```Object数组```，省略了创建数组的过程
+ 可变参数列表遇到```方法重载```可能会出现```语义模糊```的情况

## 5.9 枚举类型

+ 枚举类型实际是继承了一个抽象父类 - ```Enum```
+ 枚举类型的```values()```方法，代码中访问不到，Java编译的时候会加入到class文件中：
```bash
$ javap -v Spiciness.class
public final class cn.rumoss.study.Initialization02.Spiciness 
    extends java.lang.Enum<cn.rumoss.study.Initialization02.Spiciness>

Constant pool:
 #40 = Utf8               $VALUES
 #41 = Utf8               [Lcn/rumoss/study/Initialization02/Spiciness;
 #42 = Utf8               values
 #65 = NameAndType        #40:#41        // $VALUES:[Lcn/rumoss/study/Initialization02/Spiciness;
 
public static cn.rumoss.study.Initialization02.Spiciness[] values();
    descriptor: ()[Lcn/rumoss/study/Initialization02/Spiciness;
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=1, locals=0, args_size=0
         0: getstatic     #1                  // Field $VALUES:[Lcn/rumoss/study/Initialization02/Spiciness;
         3: invokevirtual #2                  // Method "[Lcn/rumoss/study/Initialization02/Spiciness;".clone:()Ljava/lang/Object;
         6: checkcast     #3                  // class "[Lcn/rumoss/study/Initialization02/Spiciness;"
         9: areturn
      LineNumberTable:
        line 3: 0
```
+ 由上可知，枚举类型实际上是继承了一个枚举的目标类型参数 - ```Spiciness extends Enum<Spiciness>```
+ 测试发现，枚举类型实例只会存在枚举的个数，修改其中一个实例对象的值，会影响后续取到的实例对象



---

# 7.复用类

两种方式复用类：
+ ```组合：```在一个类A中创建另外一个类B，为了使用B的功能
+ ```继承：```

## 7.1 组合语法

+ ```System.out.println```输出的没有调用String的```toString()```方法，只是简单的复制了String的字符数组 - ```java.io.BufferedWriter.write(java.lang.String, int, int)```
+ 引用变量初始化的时机-4处：
    + 对象定义的时候
    + 构造器内
    + 使用的时候，```惰性初始化```
    + 使用实例初始化 - 非静态代码块(构造器执行之前)

## 7.2 继承语法

+ 使用关键字 - ```A extends B``` A类继承B类
+ 子类构造器会默认调用父类构造器，先执行完后才会执行自己的构造器
+ 如果父类提供了有参构造函数，没有默认构造函数
    + 除非子类在其构造函数中显示调用```父类的有参构造函数```，否则编译器会提示父类没有提供无参构造函数，因为编译器默认只会调用父类的无参构造函数
```bash
public class cn.rumoss.study.ReusingClasses03.Detergent extends cn.rumoss.study.ReusingClasses03.Cleanser

Constant pool:
   #1 = Methodref          #20.#43        // cn/rumoss/study/ReusingClasses03/Cleanser."<init>":()V

public cn.rumoss.study.ReusingClasses03.Detergent();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method cn/rumoss/study/ReusingClasses03/Cleanser."<init>":()V
         4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         7: ldc           #3                  // String I am son - Detergent
         9: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        12: return
      LineNumberTable:
        line 5: 0
        line 6: 4
        line 7: 12
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      13     0  this   Lcn/rumoss/study/ReusingClasses03/Detergent;

## 看看父类，里面调用了Object的构造方法
 public cn.rumoss.study.ReusingClasses03.Cleanser();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: ldc           #2                  // String Cleanser
         7: putfield      #3                  // Field s:Ljava/lang/String;
        10: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        13: ldc           #5                  // String I am father - Cleanser
        15: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        18: return
      LineNumberTable:
        line 35: 0
        line 33: 4
        line 36: 10
        line 37: 18
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      19     0  this   Lcn/rumoss/study/ReusingClasses03/Cleanser;
```

+ 子类中重写了父类的方法，除非显式使用```super.method()```语法调用，否则不会除非父类的方法，调用的位置可以在子方法体的任何位置，不像构造器一定要在第一行调用
+ 测试发现，子类似乎存在一个父类对象的引用 - ```super```，如果通过super改变某个变量值，且子类未重新定义，则该值通过this和super获取的结果是同一个对象
+ 抛出一个问题：子类创建的时候会创建父类对象吗？
```bash
https://blog.csdn.net/banzhengyu/article/details/81039757#commentsedit
在创建子类对象时，会把父类里的成员变量和方法也加载进内存（因为要加载进内存，所以要看下这些数据是怎么初始化的，所以调用了父类的构造，仅此而已，并不是去创建了父类对象），然后用this和super这两个引用来区分是父类的还是子类的，但是这个内存区域是子类的内存区域，绝不是父类的 this指向了不仅父类可继承的成员变量和可继承的方法外，它还指向了子类的成员变量和方法 而super仅仅只是指向了子类对象中从父类继承的成员变量和方法。
```
+ 通过HSDB查看对象：```java -cp $JAVA_HOME/lib/sa-jdi.jar sun.jvm.hotspot.HSDB```


## 7.3 代理

## 7.4 结合使用组合和继承

## 7.5 在组合与继承之间选择

## 7.6 protected关键字

## 7.7 向上转型

## 7.8 final关键字

## 7.9 初始化及类的初始化

























