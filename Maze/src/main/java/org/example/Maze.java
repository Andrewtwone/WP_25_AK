package org.example;

public class Maze {

    private final int rows, cols;
    private final Room[][] rooms;
    private final MazeFactory factory;

    public Maze(int cols, int rows, int startX, int startY, MazeFactory factory) {
        this.rows = rows;
        this.cols = cols;
        this.factory = factory;
        this.rooms = new Room[rows][cols];

        // Pokoje
        int nr = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = startX + c * MapSite.ROOM_SIZE;
                int y = startY + r * MapSite.ROOM_SIZE;
                rooms[r][c] = factory.makeRoom(nr++, x, y);
            }
        }

        // Współdzielone ściany
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Room room = rooms[r][c];

                // NORTH
                if (r == 0) {
                    room.setSide(Direction.NORTH, factory.makeWall(room.getX(), room.getY(), Direction.NORTH));
                } else {
                    Room up = rooms[r - 1][c];
                    room.setSide(Direction.NORTH, up.getSide(Direction.SOUTH));
                }

                // WEST
                if (c == 0) {
                    room.setSide(Direction.WEST, factory.makeWall(room.getX(), room.getY(), Direction.WEST));
                } else {
                    Room left = rooms[r][c - 1];
                    room.setSide(Direction.WEST, left.getSide(Direction.EAST));
                }

                // SOUTH + EAST
                room.setSide(Direction.SOUTH, factory.makeWall(room.getX(), room.getY(), Direction.SOUTH));
                room.setSide(Direction.EAST,  factory.makeWall(room.getX(), room.getY(), Direction.EAST));
            }
        }

        // Wejście/wyjście
        rooms[0][0].setSide(Direction.WEST, null);
        rooms[2][2].setSide(Direction.EAST, null);

        // Drzwi
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

        Door door = factory.makeDoor(room1, room2, dir1to2);

        room1.setSide(dir1to2, door);

        Direction opposite = switch (dir1to2) {
            case NORTH -> Direction.SOUTH;
            case SOUTH -> Direction.NORTH;
            case EAST  -> Direction.WEST;
            case WEST  -> Direction.EAST;
        };
        room2.setSide(opposite, door);
    }

    public void detonateBombRooms() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Room room = rooms[r][c];
                if (room instanceof BombedRoom br) {
                    br.detonate();

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
    }

    public void draw(java.awt.Image image) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rooms[r][c].draw(image);
            }
        }
    }
}
