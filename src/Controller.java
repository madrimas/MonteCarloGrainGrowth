import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Controller {
    private int n, m;
    int[][] board;
    int[][] energy;
    Map<Integer, Integer> ID = new HashMap<>();
    private int[][] tab = new int[3][3];
    Random generator = new Random();

    Controller(int n, int m) {
        this.n = n;
        this.m = m;
        this.board = new int[this.n][this.m];
        this.energy = new int[this.n][this.m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.board[i][j] = 0;
                this.energy[i][j] = 8;
            }
        }
    }

    int moore(int x, int y, boolean w) {
        if (w) {
            tab = period(x, y);
        } else {
            tab = noPeriod(x, y);
        }
        int[] pom = proximity(tab);
        int max = 0;
        for (int i = 1; i < ID.size(); i++) {
            if (board[x][y] != ID.get(i) && pom[i] != 0) {
                max += pom[i];
            }
        }
        return max;
    }

    private int[] proximity(int[][] tab) {
        int[] idSize = new int[ID.size()];
        for (int i = 0; i < ID.size(); i++) {
            idSize[i] = 0;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != 1 || j != 1) {
                    for (int k = 0; k < ID.size(); k++) {
                        if (tab[i][j] == ID.get(k)) {
                            idSize[k]++;
                        }
                    }
                }
            }
        }
        return idSize;
    }

    private int[][] period(int x, int y) {
        //warunki periodczyne
        if (x == 0 && y == 0) {
            tab[0][0] = this.board[n - 1][m - 1];
            tab[0][1] = this.board[n - 1][y];
            tab[0][2] = this.board[n - 1][y + 1];
            tab[1][0] = this.board[x][m - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][m - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x == 0 && y == this.m - 1) {
            tab[0][0] = this.board[n - 1][y - 1];
            tab[0][1] = this.board[n - 1][y];
            tab[0][2] = this.board[n - 1][0];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][0];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][0];
        } else if (x == this.n - 1 && y == 0) {
            tab[0][0] = this.board[x - 1][m - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][m - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[0][m - 1];
            tab[2][1] = this.board[0][y];
            tab[2][2] = this.board[0][y + 1];
        } else if (x == this.n - 1 && y == this.m - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][0];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][0];
            tab[2][0] = this.board[0][y - 1];
            tab[2][1] = this.board[0][y];
            tab[2][2] = this.board[0][0];
        } else if (x == 0 && y > 0 && y < this.m - 1) {
            tab[0][0] = this.board[n - 1][y - 1];
            tab[0][1] = this.board[n - 1][y];
            tab[0][2] = this.board[n - 1][y + 1];
            ;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x > 0 && x < this.n - 1 && y == 0) {
            tab[0][0] = this.board[x - 1][m - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][m - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][m - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x == this.n - 1 && y > 0 && y < this.m - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[0][y - 1];
            tab[2][1] = this.board[0][y];
            tab[2][2] = this.board[0][y + 1];
        } else if (y == this.m - 1 && x > 0 && x < this.n - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][0];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][0];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][0];
        } else {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        }
        return tab;
    }

    private int[][] noPeriod(int x, int y) {
        if (x == 0 && y == 0) {
            tab[0][0] = 0;
            tab[0][1] = 0;
            tab[0][2] = 0;
            tab[1][0] = 0;
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = 0;
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x == 0 && y == this.m - 1) {
            tab[0][0] = 0;
            tab[0][1] = 0;
            tab[0][2] = 0;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = 0;
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = 0;
        } else if (x == this.n - 1 && y == 0) {
            tab[0][0] = 0;
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = 0;
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = 0;
            tab[2][1] = 0;
            tab[2][2] = 0;
        } else if (x == this.n - 1 && y == this.m - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = 0;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = 0;
            tab[2][0] = 0;
            tab[2][1] = 0;
            tab[2][2] = 0;
        } else if (x == 0 && y > 0 && y < this.m - 1) {
            tab[0][0] = 0;
            tab[0][1] = 0;
            tab[0][2] = 0;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x > 0 && x < this.n - 1 && y == 0) {
            tab[0][0] = 0;
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = 0;
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = 0;
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x == this.n - 1 && y > 0 && y < this.m - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = 0;
            tab[2][1] = 0;
            tab[2][2] = 0;
        } else if (y == this.m - 1 && x > 0 && x < this.n - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = 0;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = 0;
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = 0;
        } else {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        }

        return tab;
    }

    void count(int a, boolean b) {
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        int pom;
        int previous;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                previous = board[i][j];
                int rand = generator.nextInt(a) + 1;
                this.board[i][j] = rand;
                pom = moore(i, j, b);
                if (pom <= energy[i][j]) {
                    energy[i][j] = pom;
                } else {
                    board[i][j] = previous;
                }
                energy[i][j] = moore(i, j, b);
            }
        }
        long executionTime = System.currentTimeMillis() - millisActualTime; // czas wykonania programu w milisekundach.
        System.out.println("Czas count: " + executionTime);
    }

}
