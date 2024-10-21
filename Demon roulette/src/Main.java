

import java.util.Scanner;
import java.util.Random;

public class Main {
    static Player p1;
    static Player p2;
    static Gun gun;
    static int bullet_id;
    static int total_bullet;
    static int real_bullet=0;
    static int empty_bullet=0;
    static Item p1_item;
    static Item p2_item;
    static int bullet_harm;
    static int round;
    public static void main(String[] args) {
        init_screen();
        //初始化玩家,枪，道具
        init_player();
        init_gun();
        init_item();
        //运行
        run();
        
    }
    //单局游戏运行
    static void run(){
        round=1;
        System.out.println("~~~第"+round+"回合~~~");
        Gun.loading_bullet();
        show_bullet();
            while (p1.blood > 0 && p2.blood > 0) {
                //玩家1回合
                round_run(p1,p2,p1_item);
                check_bullet();
                //玩家2回合
                round_run(p2,p1,p2_item);
                check_bullet();
            }
    }
    //回合
    static void round_run(Player me, Player he,Item my_item){
        show_blood();
        show_item(my_item);
        choice_item(my_item);
        judge_item(me,he,my_item);
        player_choice(me);
        judge(me, he, me.choice);
        judge_settlement();
    }
    //游戏介绍
    static void init_screen(){
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("@@@ 欢迎来到 DEMON ROULETTE 恶魔轮盘 @@@");
        System.out.println("游戏规则：两名玩家进行回合制开枪对决---" );
        System.out.println("-----枪中有实弹和空弹，每轮枪中子弹数量随机，玩家知晓枪中实弹与空弹数量---" );
        System.out.println("-----每位玩家拥有4滴血，击中一次掉一滴血，一方空血结束游戏---" );
        System.out.println("-----玩家输入0则为对自己开枪，输入1则为对对方开枪---" );
        System.out.println("-----对自己开枪时若为空弹，则额外获得一次开枪机会---");
        System.out.println("-----游戏开始时玩家获得随机两个道具，之后每轮对决结束获得1个道具，每回合都可使用一次道具---");
        System.out.println("-----玩家输入道具袋上的序号以使用该位置的道具,输入0则不使用道具---");
        System.out.println("-----道具袋最多可存放4个道具---");
        System.out.println("---道具列表：");
        System.out.println("""
                    --手铐，使对方下回合无法行动
                    --锯子；当前子弹伤害变为2
                    --伏特加：退掉当前子弹
                    --放大镜：看到当前子弹为实弹或空弹
                    --香烟：回复一滴血（不可超过血量上限）""");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("---游戏，正式开始，，，，，，");
        System.out.println(" ");
        System.out.println(" ");
    }
    //判断游戏是否结束
    static void judge_settlement(){
        if(p1.blood <= 0 || p2.blood <= 0){
            game_settlement();
        }
    }
    //游戏结算
    static void game_settlement(){
        show_blood();
        if(p1.blood==0){
            System.out.println("====游戏结束，玩家2获得胜利====");
        } else if (p2.blood==0) {
            System.out.println("====游戏结束，玩家1获得胜利====");
        }
    }
    //初始化玩家
    static void init_player(){
        p1=new Player(4,0,1);
        p2=new Player(4,0,2);

    }
    //血量显示
    static void show_blood(){
        if(p1.blood>4){
            p1.blood=4;
        }
        if(p2.blood>4){
            p2.blood=4;
        }
        System.out.println("---玩家1还剩"+p1.get_blood()+"滴血---");
        System.out.println("---玩家2还剩"+p2.get_blood()+"滴血---");
    }
    //初始化枪
    static void init_gun(){
        bullet_harm=1;
        Random r=new Random();
        //随机枪的弹夹容量
        do{
            total_bullet =r.nextInt(4,9);
            gun = new Gun(total_bullet, new int[total_bullet]);
        }while (total_bullet %2!=0);
    }
    //创建玩家输入
    static void player_choice(Player p){
        Scanner p_Scanner=new Scanner(System.in);
        System.out.println("玩家"+p.player_ID+"，你选择射击的对象是：");
        p.choice=p_Scanner.nextInt();
    }
    //检查子弹是否打完
    static void check_bullet(){
        if(bullet_id>=Gun.bullet_list.length) {
            //子弹打完时重置
            init_gun();
            round++;
            System.out.println("~~~第" + round + "回合~~~");
            Gun.loading_bullet();
            show_bullet();
            //重新获取道具
            get_item(p1_item);
            get_item(p2_item);
            bullet_id = 0;
        }
    }

    //判断玩家是否中弹
    static void judge(Player me,Player he,int x) {
        if (x == 0) {
            if (Gun.bullet_list[bullet_id] == 0) {
                bullet_id++;
                System.out.println("[你打出了一发空弹]");
                check_bullet();
                player_choice(me);
                judge(me,he, me.choice);
            } else if (Gun.bullet_list[bullet_id] == 1) {
                System.out.println("[你打出了一发实弹]");
                me.blood= me.blood-bullet_harm;
                bullet_id++;
            }
        } else if (x == 1) {
            if (Gun.bullet_list[bullet_id] == 0) {
                System.out.println("[你打出了一发空弹]");
                bullet_id++;
            } else if (Gun.bullet_list[bullet_id] == 1) {
                System.out.println("[你打出了一发实弹]");
                he.blood=he.blood-bullet_harm;
                bullet_id++;
            }
        }
        bullet_harm=1;
    }
    //显示枪中实弹和空弹数量
    static void show_bullet(){
        for(int i=0;i<Gun.bullet_list.length;i++){
            if (Gun.bullet_list[i]==0){
                empty_bullet++;
            }else if (Gun.bullet_list[i]==1){
                real_bullet++;
            }
        }
        System.out.println("枪中有"+real_bullet+"枚实弹,"+empty_bullet+"枚空弹");
        real_bullet=0;
        empty_bullet=0;
    }
    //创建玩家道具选择
    static void choice_item(Item p){
        Scanner p_Scanner=new Scanner(System.in);
        System.out.println("玩家"+p.player_ID+"，你选择的道具是：");
        p.item_choice=p_Scanner.nextInt();
    }
    //初始化道具列表
    static void init_item(){
        p1_item= new Item(4, 0,1,new int[4], 0,0);
        p2_item= new Item(4, 0,2,new int[4], 0,0);
        get_item(p1_item);
        get_item(p1_item);
        get_item(p2_item);
        get_item(p2_item);

    }
    //道具选择判断
    //1：手铐，对方下回合无法行动
    //2：锯子；这一发子弹伤害变为2
    //3：伏特加：退掉下一发子弹
    //4：放大镜：看到这一发为实弹或空弹
    //5：香烟，回复一滴血
    static void judge_item(Player me,Player he,Item item){
        if(item.item_choice==0){
            System.out.println("玩家" + me.player_ID + "选择不使用道具");
        }else{
            switch (item.item_list[item.item_choice - 1]) {
                case 1:
                    item.item_list[item.item_choice - 1]=0;
                    System.out.println("玩家" + me.player_ID + "使用了<手铐>");
                    System.out.println("玩家" + he.player_ID + "动弹不得");
                    player_choice(me);
                    judge(me, he, me.choice);
                    break;
                case 2:
                    item.item_list[item.item_choice - 1]=0;
                    System.out.println("玩家" + me.player_ID + "使用了<锯子>");
                    System.out.println("这一发子弹伤害翻倍！");
                    bullet_harm = 2;
                    break;
                case 3:
                    item.item_list[item.item_choice - 1]=0;
                    System.out.println("玩家" + me.player_ID + "使用了<伏特加>");
                    System.out.print("你退掉了一发");
                    if (Gun.bullet_list[bullet_id] == 0) {
                        System.out.println("空弹!");
                    } else if (Gun.bullet_list[bullet_id] == 1) {
                        System.out.println("实弹!!!");
                    }
                    bullet_id++;
                    break;
                case 4:
                    item.item_list[item.item_choice - 1]=0;
                    System.out.println("玩家" + me.player_ID + "使用了<放大镜>");
                    System.out.print("这一发子弹为");
                    if (Gun.bullet_list[bullet_id] == 0) {
                        System.out.println("空弹!");
                    } else if (Gun.bullet_list[bullet_id] == 1) {
                        System.out.println("实弹!!!");
                    }
                    break;
                case 5:
                    item.item_list[item.item_choice - 1]=0;
                    System.out.println("玩家" + me.player_ID + "使用了<香烟>");
                    me.blood++;
                    show_blood();
                    break;

            }
        }
    }
    //玩家获取随机物品
    static void get_item(Item p) {
        Random r = new Random();

        if (p.item_list[p.item_random] == 0) {
            p.item_list[p.item_random] = r.nextInt(1, 6);
            p.item_random++;
            if (p.item_random == 4) {
                p.item_random = 0;
            }
        } else if (p.item_list[p.item_random] == 1) {

            if (p.item_random == 3) {
                p.item_random = 0;
                get_item(p);
            } else {
                p.item_random++;
                get_item(p);
            }
        }
    }
    //显示道具列表
    static void show_item(Item p){
        System.out.println("==玩家"+p.player_ID+"的道具袋==");
        for(int i=0;i<p.item_list.length;i++){
            int n=i+1;
                System.out.print("[" + n + "]");
                switch (p.item_list[i]) {
                    case 1:
                        System.out.print("手铐");
                        break;
                    case 2:
                        System.out.print("锯子");
                        break;
                    case 3:
                        System.out.print("伏特加");
                        break;
                    case 4:
                        System.out.print("放大镜");
                        break;
                    case 5:
                        System.out.print("香烟");
                        break;
                }
            System.out.println(" ");
        }
        System.out.println(" ");

    }
}
//创建枪类
class Gun {
    public static int bullet_number ;
    public static int[] bullet_list;
    public Gun(int n,int[]m){
        bullet_number=n;
        bullet_list=m;
    }

    //装填子弹
    static void loading_bullet(){
        //随机获取实弹和空弹
        System.out.println("手枪装弹中。。。。。。装弹完毕！！！");
        for (int i=0;i<bullet_number/2;i++){
            Random x=new Random();
            bullet_list[x.nextInt(0,bullet_list.length)]=1;
        }
        //System.out.println(Arrays.toString(bullet_list));
    }

}
//创建玩家类
class Player {
    public int blood;
    public int choice;
    public int player_ID;
    public Player(int blood,int choice,int id){
        this.blood=blood;
        this.choice=choice;
        this.player_ID=id;
    }

    int get_blood(){
        return this.blood;
    }
}
//创建道具类
class Item extends Player {
    public int[] item_list;
    public int item_choice;
    public int item_random;

    public Item(int blood, int choice, int id,int[]list,int item_choice,int item_random) {
        super(blood, choice, id);
        this.item_list=list;
        this.item_choice=item_choice;
        this.item_random=item_random;
    }
}