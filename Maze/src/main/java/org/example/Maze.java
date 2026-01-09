package org.example;

public class Maze {

    private int rows, cols;
    private Room[][] rooms;

    public Maze(int cols, int rows, int startX, int startY) {
        this.rows = rows;
        this.cols = cols;
        rooms = new Room[rows][cols];

        // 1) Tworzymy pokoje
        int nr = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = startX + c * MapSite.ROOM_SIZE;
                int y = startY + r * MapSite.ROOM_SIZE;

                // przykładowo: bomby w pokojach 5 i 9 (możesz zmienić)
                boolean bombHere = (r == 1 && c == 1) || (r == 2 && c == 2);

                rooms[r][c] = bombHere
                        ? new BombedRoom(nr++, x, y)
                        : new Room(nr++, x, y);
            }
        }

        // 2) Tworzymy współdzielone ściany
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Room room = rooms[r][c];

                // NORTH
                if (r == 0) {
                    room.setSide(Direction.NORTH, new BombedWall(room.getX(), room.getY(), Direction.NORTH, false));
                } else {
                    Room up = rooms[r - 1][c];
                    room.setSide(Direction.NORTH, up.getSide(Direction.SOUTH));
                }

                // WEST
                if (c == 0) {
                    room.setSide(Direction.WEST, new BombedWall(room.getX(), room.getY(), Direction.WEST, false));
                } else {
                    Room left = rooms[r][c - 1];
                    room.setSide(Direction.WEST, left.getSide(Direction.EAST));
                }

                // SOUTH
                room.setSide(Direction.SOUTH, new BombedWall(room.getX(), room.getY(), Direction.SOUTH, false));

                // EAST
                room.setSide(Direction.EAST, new BombedWall(room.getX(), room.getY(), Direction.EAST, false));
            }
        }

        // 3) Wejście i wyjście
        Room entrance = rooms[0][0];
        Room exit = rooms[2][2];
        entrance.setSide(Direction.WEST, null);
        exit.setSide(Direction.EAST, null);

        // 4) Drzwi
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

        room1.setSide(dir1to2, door);

        Direction opposite = switch (dir1to2) {
            case NORTH -> Direction.SOUTH;
            case SOUTH -> Direction.NORTH;
            case EAST  -> Direction.WEST;
            case WEST  -> Direction.EAST;
        };
        room2.setSide(opposite, door);
    }

    // detonacja bomb
    public void detonateBombRooms() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Room room = rooms[r][c];
                if (room instanceof BombedRoom br) {
                    br.detonate();

                    // Detonacja niszczy tylko ŚCIANY dookoła pokoju (nie drzwi)
                    detonateIfBombedWall(room.getSide(Direction.NORTH));
                    detonateIfBombedWall(room.getSide(Direction.SOUTH));
                    detonateIfBombedWall(room.getSide(Direction.WEST));
                    detonateIfBombedWall(room.getSide(Direction.EAST));
                }
            }
        }
    }

    private void detonateIfBombedWall(MapSite site) {
        if (site instanceof BombedWall bw) {
            bw.setDetonated(true);
        }
        // jeśli jest Door albo null -> nie ruszamy
    }

    public void draw(java.awt.Image image) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rooms[r][c].draw(image);
            }
        }
    }
}
