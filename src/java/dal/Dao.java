/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Account;
import model.Information;
import model.Tasks;
import org.apache.catalina.User;

/**
 *
 * @author ADMIN
 */
public class Dao extends DBContext {

    public Information checkAndGetAccount(Account a) {
        String sql = "select infoID, infoName, infoEmail, infoPhone, infoGender, infoDoB, i.userID, userName\n"
                + "from Information i, Account a\n"
                + "where i.userID = (select userID from Account where userName = ? and password = ?) and i.userID = a.userID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUser());
            st.setString(2, a.getPass());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Information i = new Information();
                i.setId(rs.getString("infoID"));
                i.setName(rs.getString("infoName"));
                i.setEmail(rs.getString("infoEmail"));
                i.setPhone(rs.getString("infoPhone"));
                i.setGender(rs.getString("infoGender"));
                i.setDob(rs.getString("infoDoB"));
                a.setUserID(rs.getString("userID"));
                a.setUser(rs.getString("userName"));
                i.setA(a);
                return i;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Tasks> getTasksByUserId(String id) {
        List<Tasks> list = new ArrayList<>();
        String sql = "select t.taskID, taskName, taskDetail, taskDate, taskTime, prioritize\n"
                + "from Task t, TaskDetail td\n"
                + "where t.taskID = td.taskID and userId = ?\n"
                + "order by taskDate asc, taskTime asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tasks t = new Tasks();
                t.setTaskID(rs.getString("taskID"));
                t.setTaskName(rs.getString("taskName"));
                t.setTaskDes(rs.getString("taskDetail"));
                t.setTaskDate(rs.getString("taskDate"));
                t.setTaskTime(rs.getString("taskTime").substring(0, 8));
                t.setPrioritize(rs.getString("prioritize"));
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Tasks getTasksByTaskId(String id) {
        String sql = "select t.taskID, taskName, taskDetail, taskDate, taskTime, prioritize\n"
                + "from Task t, TaskDetail td\n"
                + "where t.taskID = td.taskID and t.taskID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tasks t = new Tasks();
                t.setTaskID(rs.getString("taskID"));
                t.setTaskName(rs.getString("taskName"));
                t.setTaskDes(rs.getString("taskDetail"));
                t.setTaskDate(rs.getString("taskDate"));
                t.setTaskTime(rs.getString("taskTime").substring(0, 8));
                t.setPrioritize(rs.getString("prioritize"));
                return t;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean newTask(Tasks t) {
        String sql = "INSERT INTO [dbo].[Task]\n"
                + "           ([taskName]\n"
                + "           ,[taskDate]\n"
                + "           ,[taskTime]\n"
                + "           ,[prioritize]\n"
                + "           ,[userId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)\n"
                + "SELECT SCOPE_IDENTITY() as id;";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, t.getTaskName());
            st.setDate(2, Date.valueOf(t.getTaskDate()));
            st.setTime(3, Time.valueOf(t.getTaskTime()));
            st.setString(4, t.getPrioritize());
            st.setInt(5, Integer.parseInt(t.getA().getUserID()));
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                t.setTaskID(rs.getString("id"));
            }
            addTaskDetail(t);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateTask(Tasks t) {
        String sql = "UPDATE [dbo].[Task]\n"
                + "   SET [taskName] = ?\n"
                + "      ,[taskDate] = ?\n"
                + "      ,[taskTime] = ?\n"
                + "      ,[prioritize] = ?\n"
                + " WHERE taskID = ?\n"
                + "\n"
                + " UPDATE [dbo].[TaskDetail]\n"
                + "   SET [taskDetail] = ?\n"
                + " WHERE taskID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, t.getTaskName());
            st.setDate(2, Date.valueOf(t.getTaskDate()));
            st.setTime(3, Time.valueOf(t.getTaskTime()));
            st.setString(4, t.getPrioritize());
            st.setInt(5, Integer.parseInt(t.getTaskID()));
            st.setString(6, t.getTaskDes());
            st.setInt(7, Integer.parseInt(t.getTaskID()));
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateProfile(Information i) {
        String sql = "UPDATE [dbo].[Information]\n"
                + "   SET [infoName] = ?\n"
                + "      ,[infoEmail] = ?\n"
                + "      ,[infoPhone] = ?\n"
                + "      ,[infoGender] = ?\n"
                + "      ,[infoDoB] = ?\n"
                + " WHERE [userID] = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, i.getName());
            st.setString(2, i.getEmail());
            st.setString(3, i.getPhone());
            st.setString(4, i.getGender());
            st.setString(5, i.getDob());
            st.setString(6, i.getA().getUserID());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteTask(String id) {
        String sql = "DELETE FROM [dbo].[Task] WHERE taskID = ?\n"
                + "DELETE FROM [dbo].[TaskDetail] WHERE taskID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, Integer.parseInt(id));
            st.setInt(2, Integer.parseInt(id));
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean addTaskDetail(Tasks t) {
        String sql = "INSERT INTO [dbo].[TaskDetail]\n"
                + "           ([taskDetail]\n"
                + "           ,[taskID])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, t.getTaskDes());
            st.setInt(2, Integer.parseInt(t.getTaskID()));
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean register(Account a, Information i) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([userName]\n"
                + "           ,[password])\n"
                + "     VALUES\n"
                + "           (?,?)\n"
                + "SELECT SCOPE_IDENTITY() as id;";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, a.getUser());
            st.setString(2, a.getPass());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                String id = rs.getString("id");
                registerInfo(i, id);
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean registerInfo(Information i, String id) {
        String sql = "INSERT INTO [dbo].[Information]\n"
                + "           ([infoName]\n"
                + "           ,[infoEmail]\n"
                + "           ,[infoPhone]\n"
                + "           ,[infoGender]\n"
                + "           ,[infoDoB]\n"
                + "           ,[userID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, i.getName());
            st.setString(2, i.getEmail());
            st.setString(3, i.getPhone());
            st.setString(4, i.getGender());
            st.setString(5, i.getDob());
            st.setInt(6, Integer.parseInt(id));
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkUser(String user) {
        String sql = "select *\n"
                + "from Account a, Information i\n"
                + "where a.userID = i.userID and userName = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkEmail(String email) {
        String sql = "select *\n"
                + "from Account a, Information i\n"
                + "where a.userID = i.userID and infoEmail = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkPhone(String phone) {
        String sql = "select *\n"
                + "from Account a, Information i\n"
                + "where a.userID = i.userID and infoPhone = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, phone);
            st.executeUpdate();
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkForgetPassword(String phone, String email) {
        String sql = "select * \n"
                + "from Information\n"
                + "where infoEmail = ? and infoPhone = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            st.setString(2, phone);
            st.executeUpdate();
            int count = 0;
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count++;
            }
            if (count == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean changePasswordForget(String pass, String email, String phone) {
        String sql = "update Account set password = ? "
                + "where userID = (select userID from Information where infoEmail = ? and infoPhone = ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, pass);
            st.setString(2, email);
            st.setString(3, phone);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean changePassword(String id, String n) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?\n"
                + " WHERE userID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, n);
            st.setInt(2, Integer.parseInt(id));
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public List<List<Tasks>> get(String id) {
        List<List<Tasks>> list = new ArrayList<>();
        List<Tasks> list1 = new ArrayList<>();
        String sql = "select t.taskID, taskName, taskDetail, taskDate, taskTime, prioritize\n"
                + "from Task t, TaskDetail td\n"
                + "where t.taskID = td.taskID and userId = ?\n"
                + "order by taskDate desc, taskTime asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tasks t = new Tasks();
                t.setTaskID(rs.getString("taskID"));
                t.setTaskName(rs.getString("taskName"));
                t.setTaskDes(rs.getString("taskDetail"));
                String date = rs.getString("taskDate");
                t.setTaskDate(date);
                t.setTaskTime(rs.getString("taskTime").substring(0, 8));
                t.setPrioritize(rs.getString("prioritize"));
                if (!list1.isEmpty() && !date.equals(list1.get(list1.size() - 1).getTaskDate())) {
                    List<Tasks> list2 = new ArrayList<>();
                    for (int i = 0; i < list1.size(); i++) {
                        list2.add(list1.get(i));
                    }
                    list.add(list2);
                    list1.clear();
                }
                list1.add(t);
            }
            list.add(list1);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<List<Tasks>> getDown(String id) {
        List<List<Tasks>> list = new ArrayList<>();
        List<Tasks> list1 = new ArrayList<>();
        String sql = "select t.taskID, taskName, taskDetail, taskDate, taskTime, prioritize\n"
                + "from Task t, TaskDetail td\n"
                + "where t.taskID = td.taskID and userId = ?\n"
                + "order by taskDate asc, taskTime asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tasks t = new Tasks();
                t.setTaskID(rs.getString("taskID"));
                t.setTaskName(rs.getString("taskName"));
                t.setTaskDes(rs.getString("taskDetail"));
                String date = rs.getString("taskDate");
                t.setTaskDate(date);
                t.setTaskTime(rs.getString("taskTime").substring(0, 8));
                t.setPrioritize(rs.getString("prioritize"));
                if (!list1.isEmpty() && !date.equals(list1.get(list1.size() - 1).getTaskDate())) {
                    List<Tasks> list2 = new ArrayList<>();
                    for (int i = 0; i < list1.size(); i++) {
                        list2.add(list1.get(i));
                    }
                    list.add(list2);
                    list1.clear();
                }
                list1.add(t);
            }
            list.add(list1);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Tasks> getTasksByUserIdAndSearch(String id, String search) {
        List<Tasks> list = new ArrayList<>();
        String sql = "select t.taskID, taskName, taskDetail, taskDate, taskTime, prioritize, userId\n"
                + "from Task t, TaskDetail td\n"
                + "where t.taskID = td.taskID and userId = ? and taskDetail like ? or\n"
                + "t.taskID = td.taskID and userId = ? and taskName like ?\n"
                + "order by taskDate asc, taskTime asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            st.setString(2, "%" + search + "%");
            st.setInt(3, Integer.parseInt(id));
            st.setString(4, "%" + search + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tasks t = new Tasks();
                t.setTaskID(rs.getString("taskID"));
                t.setTaskName(rs.getString("taskName"));
                t.setTaskDes(rs.getString("taskDetail"));
                t.setTaskDate(rs.getString("taskDate"));
                t.setTaskTime(rs.getString("taskTime").substring(0, 8));
                t.setPrioritize(rs.getString("prioritize"));
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Tasks> getTasksByUserIdAndSort(String id, String sort) {
        List<Tasks> list = new ArrayList<>();
        String sql = "select t.taskID, taskName, taskDetail, taskDate, taskTime, prioritize, userId\n"
                + "from Task t, TaskDetail td\n"
                + "where t.taskID = td.taskID and userId = ? \n"
                + "order by prioritize "+sort+" , taskTime asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tasks t = new Tasks();
                t.setTaskID(rs.getString("taskID"));
                t.setTaskName(rs.getString("taskName"));
                t.setTaskDes(rs.getString("taskDetail"));
                t.setTaskDate(rs.getString("taskDate"));
                t.setTaskTime(rs.getString("taskTime").substring(0, 8));
                t.setPrioritize(rs.getString("prioritize"));
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        Dao d = new Dao();
        System.out.println(d.getTasksByUserIdAndSort("1", "asc"));
    }

}
