package uas_parkpbo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class todoList {
     private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    private InputStreamReader InputStreamReader = new InputStreamReader(System.in);
    private BufferedReader input = new BufferedReader(InputStreamReader);

    public todoList(Connection conn, Statement stmt) {
        this.conn = conn;
        this.stmt = stmt;
    }
    
    public void showMenu() {
        try {
            String sql = "SELECT * FROM todo_list";
            rs = stmt.executeQuery(sql);

            while (!conn.isClosed()) {
                System.out.println("\n========= MENU UTAMA =========");
                System.out.println("1. Insert Todo List");
                System.out.println("2. Lihat Todo List");
                System.out.println("3. Edit Todo List");
                System.out.println("4. Delete Todo List");
                System.out.println("0. Keluar");
                System.out.println("");
                System.out.print("PILIHAN> ");

                int pilihan = Integer.parseInt(input.readLine());

                switch (pilihan) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        insertTodoList();
                        break;
                    case 2:
                        lihatTodoList();
                        break;
                    case 3:
                        editTodoList();
                        break;
                    case 4:
                        deleteTodoList();
                        break;
                    default:
                        System.out.println("Pilihan salah!");
                }
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     private void lihatTodoList() {
        String sql = "SELECT * FROM todo_list";
        try {
            rs = stmt.executeQuery(sql);

            System.out.println("|--------------------------------|");
            System.out.println("|      TODO LIST                 |");
            System.out.println("|--------------------------------|");
            while (rs.next()) {
                int id = rs.getInt("id");
                String todo = rs.getString("todo");
                String kategori = rs.getString("kategori");
                String tanggal_selesai = rs.getString("tanggal_selesai");
                String status = rs.getString("status");

                System.out.println(String.format(" id \t\t   :%d \n todo \t   :%s \n kategori \t   :%s \n tanggal_selesai :%s \n status :%s \n ", id, todo, kategori, tanggal_selesai,status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     private void insertTodoList() {
        try {
            System.out.print("ID : ");
            int id = Integer.parseInt(input.readLine());
            System.out.print("Todo : ");
            String todo = input.readLine().trim();
            System.out.print("Kategori : ");
            String kategori = input.readLine().trim();
            System.out.print("Tanggal Selesai : ");
            String tanggal_selesai = input.readLine().trim();
            System.out.print("Status : ");
            String status = input.readLine().trim();

            String sql = "INSERT INTO todo_list (id,todo,kategori,tanggal_selesai,status) VALUE ('%d','%s','%s','%s','%s')";
            sql = String.format(sql, id, todo, kategori, tanggal_selesai,status);

            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    private void editTodoList() {
        try {
            System.out.print("ID : ");
            int id = Integer.parseInt(input.readLine());
            System.out.print("Todo : ");
            String todo = input.readLine().trim();
            System.out.print("Kategori : ");
            String kategori = input.readLine().trim();
            System.out.print("Tanggal Selesai : ");
            String tanggal_selesai = input.readLine().trim();
            System.out.print("Status : ");
            String status = input.readLine().trim();

            String sql = "UPDATE todo_list SET todo='%s',kategori='%s',tanggal_selesai='%s',status='%s'  WHERE id='%d'";
            sql = String.format(sql, todo, kategori, tanggal_selesai, status,id);

            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     private void deleteTodoList() {
        try {
            System.out.print("Todo List yang ingin dihapus: ");
            int id = input.read();;

            String sql = String.format("DELETE FROM todo_list WHERE id ='%d'", id);

            stmt.execute(sql);

            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
