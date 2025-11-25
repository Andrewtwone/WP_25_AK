package org.example;

public class Maze {

    private int rows, cols;
    private Room[][] rooms;

    public Maze(int cols, int rows, int startX, int startY) {
        this.rows = rows;
        this.cols = cols;
        rooms = new Room[rows][cols];

        // 1. Tworzymy pokoje
        int nr = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = startX + c * MapSite.ROOM_SIZE;
                int y = startY + r * MapSite.ROOM_SIZE;
                rooms[r][c] = new Room(nr++, x, y);
            }
        }

        // 2. Tworzymy współdzielone ściany
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                Room room = rooms[r][c];

                // NORTH wall
                if (r == 0) { // ściana zewnętrzna
                    room.setSide(Direction.NORTH, new Wall(room.getX(), room.getY(), Direction.NORTH));
                } else { // współdzielona z pokojem powyżej
                    Room up = rooms[r - 1][c];
                    room.setSide(Direction.NORTH, up.getSide(Direction.SOUTH));
                }

                // WEST wall
                if (c == 0) {
                    room.setSide(Direction.WEST, new Wall(room.getX(), room.getY(), Direction.WEST));
                } else {
                    Room left = rooms[r][c - 1];
                    room.setSide(Direction.WEST, left.getSide(Direction.EAST));
                }

                // SOUTH wall
                room.setSide(Direction.SOUTH, new Wall(room.getX(), room.getY(), Direction.SOUTH));

                // EAST wall
                room.setSide(Direction.EAST, new Wall(room.getX(), room.getY(), Direction.EAST));
            }
        }

        Room entrance = rooms[0][0];
        Room exit = rooms[2][2];
        entrance.setSide(Direction.WEST, null);
        exit.setSide(Direction.EAST, null);

        // 3. Drzwi (jak w Twoim obrazku)
        connect(0, 0, 0, 1, Direction.EAST);
        connect(0, 1, 0, 2, Direction.EAST);
        connect(0, 2, 1, 2, Direction.SOUTH);

        connect(1, 0, 1, 1, Direction.EAST);
        connect(1, 1, 1, 2, Direction.EAST);

        connect(1, 1, 2, 1, Direction.SOUTH);
        connect(2, 1, 2, 2, Direction.EAST);
        connect(2, 0, 2, 1, Direction.EAST);
    }

    private void connect(int r1, int c1, int r2, int c2, Direction dir1to2) {

        Room room1 = rooms[r1][c1];
        Room room2 = rooms[r2][c2];

        Door door = new Door(room1, room2, dir1to2);

        // zastępujemy ścianę drzwiami
        room1.setSide(dir1to2, door);

        Direction opposite = switch (dir1to2) {
            case NORTH -> Direction.SOUTH;
            case SOUTH -> Direction.NORTH;
            case EAST  -> Direction.WEST;
            case WEST  -> Direction.EAST;
        };
        room2.setSide(opposite, door);
    }

    public void draw(java.awt.Image image) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rooms[r][c].draw(image);
            }
        }
    }
}
