package cn.liusk.oldtest.chessboard;

/**??????
 * 1??????????????????????
 * 2??????????????????????
 * 3??????????????????????????????????
 */
public class CoordinateSystem {
    //????????
    private static CoordinateSystem coordinateSystem = null;

    private CoordinateSystem() {
    }

    public static CoordinateSystem getInstance() {
        if (coordinateSystem == null)
            coordinateSystem = new CoordinateSystem();
        return coordinateSystem;
    }

    //?????????
    private static Coordinate[][] coordinates = new Coordinate[5][5];

    //????????????????
    private final static Coordinate keyCoordinate1 = new Coordinate(1, 1);
    private final static Coordinate keyCoordinate2 = new Coordinate(1, 3);
    private final static Coordinate keyCoordinate3 = new Coordinate(3, 1);
    private final static Coordinate keyCoordinate4 = new Coordinate(3, 3);

    public Coordinate getCoordinate(int x, int y) {
        //System.out.println(coordinates[x][y].getPoint());
        return coordinates[x][y];
    }

    public String print() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[0].length; j++) {
                if (coordinates[i][j].isFull()) {
                    if (coordinates[i][j].getPoint().getC() == 0) {
                        if (j < 4) {
                            System.out.print("O" + "---");
                            sb.append("O" + "---");
                        } else {
                            System.out.print("O");
                            sb.append("O");
                        }
                    } else {
                        if (j < 4) {
                            System.out.print("X" + "---");
                            sb.append("X" + "---");
                        } else {
                            System.out.print("X");
                            sb.append("X");
                        }
                    }
                } else {
                    if (j < 4) {
                        System.out.print("|---");
                        sb.append("|---");
                    } else {
                        System.out.print("|");
                        sb.append("|");
                    }
                }
            }
            System.out.println();
            sb.append('\n');
            if (i < 4) {
                for (int k = 0; k < 5; k++) {
                    System.out.print("|   ");
                    sb.append("|   ");
                }
            }
            System.out.println();
            sb.append('\n');
        }
        System.out.println("======================");
        sb.append("======================\n");
        return sb.toString();
    }

    public boolean clearPoint(Point point) {
        int x = point.getX();
        int y = point.getY();

        CoordinateSystem.coordinates[x][y].setFull(false);
        CoordinateSystem.coordinates[x][y].setPoint(null);
        return true;
    }

    public boolean setPoint(Point point) {
        /*Coordinate c = new Coordinate();
        c.setFull(true);
        c.setPoint(point);*/

        int x = point.getX();
        int y = point.getY();
        CoordinateSystem.coordinates[x][y].setPoint(point);
        CoordinateSystem.coordinates[x][y].setFull(true);
        return true;
    }

    public boolean clear() {
        int length = CoordinateSystem.coordinates.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < CoordinateSystem.coordinates[i].length; j++) {
                CoordinateSystem.coordinates[i][j] = null;
            }
        }
        return true;
    }

    public Coordinate[][] initCoordinateSys() {
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[0].length; j++) {
                Coordinate c = new Coordinate();
                c.setX(i);
                c.setY(j);
                c.setFull(false);
                c.setPoint(null);
                coordinates[i][j] = c;
            }
        }
        int xlen = CoordinateSystem.coordinates[0].length;
        int ylen = CoordinateSystem.coordinates.length;
        for (int i = 0; i < xlen; i++) {
            Coordinate c = new Coordinate();
            c.setX(0);
            c.setY(i);
            c.setFull(true);
            c.setPoint(new Point(0, i, 0));
            CoordinateSystem.coordinates[0][i] = c;
        }
        for (int i = 0; i < xlen; i++) {
            Coordinate c = new Coordinate();
            c.setX(0);
            c.setY(i);
            c.setFull(true);
            c.setPoint(new Point(0, i, 1));
            CoordinateSystem.coordinates[ylen - 1][i] = c;
        }
        return coordinates;
    }

    public boolean isExistLine(Coordinate startCoordinate, Coordinate endCoordinate) {
        int startX = startCoordinate.getX();
        int startY = startCoordinate.getY();
        int endX = endCoordinate.getX();
        int endY = endCoordinate.getY();
        int x_x = Math.abs(endX - startX);
        int y_y = Math.abs(endY - startY);

        if (x_x == 0 || y_y == 0) {
            return true;
        } else {
            boolean flag1 = isAcrossCoordinate(keyCoordinate1, startCoordinate, endCoordinate);
            boolean flag2 = isAcrossCoordinate(keyCoordinate2, startCoordinate, endCoordinate);
            boolean flag3 = isAcrossCoordinate(keyCoordinate3, startCoordinate, endCoordinate);
            boolean flag4 = isAcrossCoordinate(keyCoordinate4, startCoordinate, endCoordinate);
            if ((flag1 || flag2 || flag3 || flag4) && x_x == y_y) {
                return true;
            } else
                return false;
        }

    }

    public static boolean isAcrossCoordinate(Coordinate coordinate, Coordinate startCoordinate,
                                             Coordinate endCoordinate) {
        if (coordinate.equals(startCoordinate) || coordinate.equals(endCoordinate))
            return true;

        double p_s = distance(coordinate, startCoordinate);
        double p_e = distance(coordinate, endCoordinate);
        double s_e = distance(startCoordinate, endCoordinate);

        if (Math.abs(p_s + p_e - s_e) < 0.1)
            return true;

        return false;
    }

    public static double distance(Coordinate startCoordinate, Coordinate endCoordinate) {
        int startX = startCoordinate.getX();
        int startY = startCoordinate.getY();
        int endX = endCoordinate.getX();
        int endY = endCoordinate.getY();
        int x_x = Math.abs(endX - startX);
        int y_y = Math.abs(endY - startY);
        double distance = Math.sqrt(x_x * x_x + y_y * y_y);
        return distance;
    }

    /**????????????????isExistLine???
     */
    public boolean isSeparate(Coordinate startCoordinate, Coordinate endCoordinate) {
        int startX = startCoordinate.getX();
        int startY = startCoordinate.getY();
        int endX = endCoordinate.getX();
        int endY = endCoordinate.getY();
        int x_x = Math.abs(endX - startX);
        int y_y = Math.abs(endY - startY);

        //??????????????????????
        if (x_x == 0 && y_y == 0) {
            return false;
        } else {

            //??????????????????????????
            if (x_x == 0) {
                int cY = startY;
                int yabsY = (endY - startY) / y_y;
                for (int i = 0; i < y_y; i++) {
                    cY += yabsY;
                    Coordinate c = coordinates[startX][cY];
                    if (c.isFull())
                        return true;
                }
            }

            //??????????????????????????
            if (y_y == 0) {
                int cX = startX;
                int xabsX = (endX - startX) / x_x;
                for (int i = 0; i < x_x; i++) {
                    cX = cX + xabsX;
                    Coordinate c = coordinates[cX][startY];
                    if (c.isFull())
                        return true;
                }
            }

            //??45?????????????
            if (x_x == y_y) {
                int xabsX = (endX - startX) / x_x;
                int yabsY = (endY - startY) / y_y;
                int cX = startX;
                int cY = startY;
                for (int i = 0; i < x_x; i++) {
                    cX += xabsX;
                    cY += yabsY;
                    Coordinate c = coordinates[cX][cY];
                    if (c.isFull())
                        return true;
                }
            }
        }

        return false;
    }

    //????????????????????
    public boolean isThreePointLine(Coordinate c1, Coordinate c2, Coordinate c3) {
        return isAcrossCoordinate(c1, c2, c3);
    }

    //?????????????????
    public boolean isFillPoint(Coordinate coordinate) {
        if (isExistCoordinate(coordinate)) {
            //System.out.println(coordinates[3][0].getPoint().getC());
            return coordinates[coordinate.getX()][coordinate.getY()].isFull();
        } else
            return false;
    }

    public boolean isExistCoordinate(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        if (x < 0 || x > 4 || y < 0 || y > 4)
            return false;
        else
            return true;
    }
}
