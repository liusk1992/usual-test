/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.oldtest.chessboard;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @author liusk
 * @version $Id: ChessSingle.java, v 0.1 2018/5/24 16:42 liusk Exp $
 */
public class ChessSingle {

    public static void main(String[] args) {
        /*CoordinateSystem cs = CoordinateSystem.getInstance();
        cs.initCoordinateSys();
        System.out.println("��ʼ��");
        cs.print();
        Scanner scanner = new Scanner(System.in);
        try{
            while(true){
                String line = scanner.nextLine();
                if("88".equals(line))
                    break;
                String outLine = chuli(cs,line);
                System.out.println(outLine);
            }
            scanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("����ر�");*/
        test();
    }

    public static String chuli(CoordinateSystem cs, String line){
        try{
            String start = line.split(",")[0];
            String end = line.split(",")[1];

            int startX = Integer.parseInt(start.split(" ")[0]);
            int startY = Integer.parseInt(start.split(" ")[1]);
            int endX = Integer.parseInt(end.split(" ")[0]);
            int endY = Integer.parseInt(end.split(" ")[1]);

            Point startPoint = new Point(startX,startY,0);
            Coordinate endCoor = new Coordinate(endX,endY);
            String outLine = move(cs,startPoint,endCoor);
            return outLine;
        }catch(Exception e){
            e.printStackTrace();
            return "�����ʽ����";
        }

    }

    public static void test() {

        CoordinateSystem cs = CoordinateSystem.getInstance();
        cs.initCoordinateSys();
        System.out.println("��ʼ���������");
        cs.print();
        //Coordinate[][] coordinates = cs.initCoordinateSys();
        System.out.println(cs.print());

        Point p1 = new Point(4,4,1);
        Coordinate c1 = new Coordinate(3,4);
        move(cs,p1,c1);


        //�ڶ���
        Point p2 = new Point(4,2,1);
        Coordinate c2 = new Coordinate(3,2);
        move(cs,p2,c2);


        //������
        Point p3 = new Point(0,0,0);
        Coordinate c3 = new Coordinate(3,0);
        move(cs,p3,c3);

        //���Ĳ�
        Point p4 = new Point(4,1,1);
        Coordinate c4 = new Coordinate(3,1);
        move(cs,p4,c4);

        //System.exit(0);
        //���岽
        Point p5 = new Point(0,3,0);
        Coordinate c5 = new Coordinate(3,3);
        move(cs,p5,c5);


		/*Point basePoint = new Point(c6.getX(),c6.getY(),p6.getC());
		setPoint(cs,basePoint);*/



    }

    public static String move(CoordinateSystem cs, Point point,Coordinate coor){
        Coordinate startCoordinate = new Coordinate(point.getX(),point.getY());
        Coordinate endCoordinate = coor;
        Coordinate csCoor = cs.getCoordinate(point.getX(),point.getY());
        if(!csCoor.isFull())
            return "�����ڸ�����";
        if(csCoor.getPoint().getC()!=point.getC())
            return "û��Ȩ���ƶ�������";
        //����֮����ֱ��
        if(cs.isExistLine(startCoordinate, endCoordinate)){
            //����֮��û�б��������Ӹ���
            if(!cs.isSeparate(startCoordinate, endCoordinate)){
                cs.clearPoint(point);
                Point p2 = new Point(endCoordinate.getX(),endCoordinate.getY(),point.getC());
                setPoint(cs,p2);
                System.out.println();
                System.out.println("��һ���ɹ�");
                return cs.print();
            }else{
                System.out.println();
                //System.out.println("����֮�����ֱ�ߵ��Ǳ�����");
                return "����֮�����ֱ�ߵ��Ǳ�����";
            }
        }else{
            System.out.println();
            System.out.println("����֮��û��������ֱ��");
            return "����֮��û��������ֱ��";
        }
    }

    public static List<Map<String,Coordinate>> isOnePointToTwoPoint(CoordinateSystem cs, Point point){
        List<Map<String,Coordinate>> list = new ArrayList<Map<String,Coordinate>>();
        Coordinate[][] coors = new Coordinate[3][3];
        int x = point.getX();
        int y = point.getY();
        int m = 0;
        for(int i=x-1;i<=x+1;i++){
            int n = 0;
            for(int j=y-1;j<=y+1;j++){
                coors[m][n] = new Coordinate(i,j);
                n++;
            }
            m++;
        }
        Coordinate baseCoor = cs.getCoordinate(x, y);

        boolean flag1 = cs.isFillPoint(coors[0][0])&&cs.isFillPoint(coors[2][2])
                &&(cs.getCoordinate(coors[0][0].getX(), coors[0][0].getY()).getPoint().getC()==cs.getCoordinate(coors[2][2].getX(), coors[2][2].getY()).getPoint().getC())
                &&(cs.getCoordinate(coors[0][0].getX(), coors[0][0].getY()).getPoint().getC()!=baseCoor.getPoint().getC())
                &&(cs.isExistLine(coors[0][0], coors[2][2]));

        boolean flag2 = cs.isFillPoint(coors[0][1])&&cs.isFillPoint(coors[2][1])
                &&(cs.getCoordinate(coors[0][1].getX(), coors[0][1].getY()).getPoint().getC()==cs.getCoordinate(coors[2][1].getX(), coors[2][1].getY()).getPoint().getC())
                &&(cs.getCoordinate(coors[0][1].getX(), coors[0][1].getY()).getPoint().getC()!=baseCoor.getPoint().getC())
                &&(cs.isExistLine(coors[0][1],coors[2][1]));

        boolean flag3 = cs.isFillPoint(coors[0][2])&&cs.isFillPoint(coors[2][0])
                &&(cs.getCoordinate(coors[0][2].getX(), coors[0][2].getY()).getPoint().getC()==cs.getCoordinate(coors[2][0].getX(), coors[2][0].getY()).getPoint().getC())
                &&(cs.getCoordinate(coors[0][2].getX(), coors[0][2].getY()).getPoint().getC()!=baseCoor.getPoint().getC())
                &&(cs.isExistLine(coors[0][2], coors[2][0]));

        boolean flag4 = cs.isFillPoint(coors[1][0])&&cs.isFillPoint(coors[1][2])
                &&(cs.getCoordinate(coors[1][0].getX(), coors[1][0].getY()).getPoint().getC()==cs.getCoordinate(coors[1][2].getX(), coors[1][2].getY()).getPoint().getC())
                &&(cs.getCoordinate(coors[1][0].getX(), coors[1][0].getY()).getPoint().getC()!=baseCoor.getPoint().getC())
                &&(cs.isExistLine(coors[1][0], coors[1][2]));



        if(flag1){
            Map<String,Coordinate> map = new HashMap<String,Coordinate>();
            map.put("first", coors[0][0]);
            map.put("second", coors[2][2]);
            list.add(map);
        }

        if(flag2){
            Map<String,Coordinate> map = new HashMap<String,Coordinate>();
            map.put("first", coors[0][1]);
            map.put("second", coors[2][1]);
            list.add(map);
        }
        if(flag3){
            Map<String,Coordinate> map = new HashMap<String,Coordinate>();
            map.put("first", coors[0][2]);
            map.put("second", coors[2][0]);
            list.add(map);
        }
        if(flag4){
            Map<String,Coordinate> map = new HashMap<String,Coordinate>();
            map.put("first", coors[1][0]);
            map.put("second", coors[1][2]);
            list.add(map);
        }
        return list;
    }

    public static List<Coordinate> isTwoPointToOnePoint(CoordinateSystem cs,Point point){
        List<Coordinate> list = new ArrayList<Coordinate>();
        Coordinate[][] coors = new Coordinate[3][3];
        int x = point.getX();
        int y = point.getY();
        int m = 0;
        for(int i=x-1;i<=x+1;i++){
            int n = 0;
            for(int j=y-1;j<=y+1;j++){
                coors[m][n] = new Coordinate(i,j);
                n++;
            }
            m++;
        }
        Coordinate baseCoor = cs.getCoordinate(x, y);


        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                boolean flag = baseCoor.getX()==coors[i][j].getX()&&baseCoor.getY()==coors[i][j].getY();
                if(cs.isFillPoint(coors[i][j])){
                    if(!flag){
                        int xx = coors[i][j].getX() - baseCoor.getX();
                        int yy = coors[i][j].getY() - baseCoor.getY();
                        int tCoorX = coors[i][j].getX() + xx;
                        int tCoorY = coors[i][j].getY() + yy;
                        Coordinate tCoor = new Coordinate(tCoorX,tCoorY);
                        if(cs.isFillPoint(tCoor)&&(cs.getCoordinate(tCoorX, tCoorY).getPoint().getC()==point.getC())){
                            list.add(coors[i][j]);
                        }
                    }
                }
            }
        }
        return list;
    }

    public static boolean setPoint(CoordinateSystem cs,Point point){

        if(cs.getCoordinate(point.getX(), point.getY()).isFull()
                &&cs.getCoordinate(point.getX(), point.getY()).getPoint().getC()==point.getC())
            return true;
        cs.setPoint(point);
        List<Map<String,Coordinate>> one2TwoList = isOnePointToTwoPoint(cs, point);
        List<Coordinate> two2OneList = isTwoPointToOnePoint(cs, point);

        //������Ӻ͵���ͬʱ����ʱ��������Ӻ͵���û���غ��ӣ���ôͬʱ���м��Ӻ͵���
        //������Ӻ͵������غ��ӣ���ôѡ����Ӻ͵�������֮һ������

        if(one2TwoList.size()>0&&two2OneList.size()>0){
            boolean isOver = false;
            for(Map<String,Coordinate> m : one2TwoList){
                Coordinate coor1 = m.get("first");
                Coordinate coor2 = m.get("second");
                for(Coordinate c : two2OneList){
                    if(c.equals(coor1)){//������Ӻ͵��������غϣ���
                        System.out.println("��ѡ����ӻ��ǵ���");
                        Scanner sc = new Scanner(System.in);
                        int a = sc.nextInt();
                        if(a==0){//����
                            setPoint(cs, new Point(coor1.getX(),coor1.getY(),point.getC()));
                            setPoint(cs, new Point(coor2.getX(),coor2.getY(),point.getC()));
                        }else{//����
                            setPoint(cs,new Point(c.getX(),c.getY(),point.getC()));
                        }
                        isOver = true;
                    }
                }
            }
            if(!isOver){//���Ӻ͵���ͬʱ����ʱ��û���غ���
                for(Map<String,Coordinate> m : one2TwoList){
                    Coordinate coor1 = m.get("first");
                    Coordinate coor2 = m.get("second");
                    setPoint(cs, new Point(coor1.getX(),coor1.getY(),point.getC()));
                    setPoint(cs, new Point(coor2.getX(),coor2.getY(),point.getC()));
                }
                for(Coordinate m : two2OneList){
                    setPoint(cs,new Point(m.getX(),m.getY(),point.getC()));
                }
            }
        }else{
            //���ֻ�е���
            if(one2TwoList.size()>0){
                for(Map<String,Coordinate> m : one2TwoList){
                    Coordinate coor1 = m.get("first");
                    Coordinate coor2 = m.get("second");
                    setPoint(cs, new Point(coor1.getX(),coor1.getY(),point.getC()));
                    setPoint(cs, new Point(coor2.getX(),coor2.getY(),point.getC()));
                }
            }

            //���ֻ�м���
            if(two2OneList.size()>0){
                for(Coordinate m : two2OneList){
                    setPoint(cs,new Point(m.getX(),m.getY(),point.getC()));
                }
            }
        }
        return true;
    }

}
