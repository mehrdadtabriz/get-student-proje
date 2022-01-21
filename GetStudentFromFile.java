package getStudents;

import sun.security.x509.InvalidityDateExtension;

import static getStudents.GetStudentsDynamicArray.addStudent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GetStudentFromFile {

    static Student[] stArr = null;
    static String fileName;
    static Scanner in = new Scanner(System.in);


    public static void main(String[] args) throws IOException {






        //








        System.out.print("Enter student data file name:)");
        fileName = in.nextLine();
        readFromFile();
        myLoop:
        while (true) {
            System.out.println("-------------بنی ادم اعضای یکدیگر است-------------");
            System.out.println("1:Add new student\n2:Edit student\n3:Delete student\n4:Sort students\n5:Show List\n6:Save to file\n7:Exit");
            System.out.print("Enter Your Choice:)");
            int userChoice = in.nextInt();
            switch (userChoice) {
                case 1:
                    AddNewStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    sortMenu();
                    sortByNameASC();
                    break;
                case 5:
                    showList();
                    break;
                case 6:
                    saveToFile();
                    break;
                case 7:
                    break myLoop;
            }
        }

    }

    //main
    public static void sortMenu() {
        int x=0;
        do {
            System.out.println("1.Sort By Name ASC");
            System.out.println("2.Sort By Name DESC");
            System.out.println("3.Sort By Student Code ASC");
            System.out.println("4.Sort By Student Code DESC");
            System.out.println("5.Sort By Field ASC");
            System.out.println("6.Sort By Field DESC");
            System.out.println("7.Sort By Total Grade ASC");
            System.out.println("8.Sort By Total Grade DESC");
            x = in.nextInt();
        }while (x>8 || x<1);
        switch (x){
            case 1 : sortByNameASC();
            break;
            case 2 : sortByNameDESC();
            break;
            case 3 : sortByStudentCodeASC();
            break;
            case 4 : sortByStudentCodeDESC();
            break;
            case 5 : sortByFieldASC();
            break;
            case 6 : sortByFieldDESC();
            break;
            case 7 : sortByTotalGradeASC();
            break;
            case 8 : sortByTotalGradeDESC();
            break;
        }
        showList();
    }
    public static void editStudent(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a Student code:");
        long stCode = in.nextLong();
        in.nextLine();
        int index = -1;
        for (int i = 0; i <stArr.length ; i++) {
            if (stArr[i].stCode == stCode){
                index = i;
                break;
            }
        }
        if (index == -1){
            System.out.println("Student not found.");
            editStudent();
        }
        System.out.print("Enter new Students Name:");
        stArr[index].stName = in.nextLine();
        System.out.print("Enter new Students Field:");
        stArr[index].stField = in.nextLine();
        System.out.print("Enter new Students Toatl Grade:");
        stArr[index].totalGrade = in.nextDouble();
        System.out.println("Student updated.");
    }
    public static void deleteStudent() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a Student code:");
        long stCode = in.nextLong();
        in.nextLine();
        int index = -1;
        for (int i = 0; i <stArr.length ; i++) {
            if (stArr[i].stCode == stCode){
                index = i;
                break;
            }
        }
        if (index == -1){
            System.out.println("Student not found:(");
            editStudent();
        }else {
            Student students[] = new Student[stArr.length - 1];
            int j = 0;
            for (int i = 0; i < stArr.length; i++) {
                if (i == index)
                    continue;
                students[j] = stArr[i];
                j++;
            }
            stArr = students;
            saveToFile();
        }
    }
    static void showList() {
        for (int i = 0; stArr != null && i < stArr.length; i++) {
            System.out.println(stArr[i]);

        }

    }
    static void readFromFile() throws FileNotFoundException {
        File f = new File(fileName);
        if (f.exists()) {
            Scanner fin = new Scanner(f);
            while (fin.hasNext()) {
                String stLine = fin.nextLine();
                stArr = addStudent(stArr, getStudent(stLine));
            }
        }
    }//readFromFile
    static void saveToFile() throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        if (f.exists()) {
           StringBuilder sb = new StringBuilder();
            for (int i = 0; i < stArr.length ; i++) {
                String s = stArr[i].stCode+","+stArr[i].stName+","+stArr[i].stField+","+stArr[i].totalGrade;
                sb.append(s+"\n");
            }
            fw.write(sb.toString());
            fw.close();
        }
    }//
    static Student getStudent(String line) {
        String[] arr = line.split(",");
        Student st = new Student();
        st.stCode = Long.parseLong(arr[0]);
        st.stName = arr[1];
        st.stField = arr[2];
        st.totalGrade = Double.parseDouble(arr[3]);

        return st;
    }//getStudent
    static Student[] addStudent(Student[] arr, Student st) {
        if (arr == null) {
            arr = new Student[1];
        } else {
            arr = Arrays.copyOf(arr, arr.length + 1);
        }
        arr[arr.length - 1] = st;
        return arr;

    }//addStudent
    static void AddNewStudent(){
        Student st = new Student();
        Scanner in = new Scanner(System.in);    
        System.out.print("Enter new Students Code:");
            st.stCode = in.nextLong();
            in.nextLine();
            System.out.print("Enter new Students Name:");
            st.stName = in.nextLine();
            System.out.print("Enter new Students Field:");
            st.stField = in.nextLine();
            System.out.print("Enter new Students Toatl Grade:");
            st.totalGrade = in.nextDouble();

            stArr = addStudent(stArr, st);
    }//AddNewStudent
    public static void sortByNameASC(){
        if (stArr !=null){
            for (int i = 0; i < stArr.length; i++) {
                for (int j = i+1; j < stArr.length; j++) {
                    if (stArr[i].stName.compareToIgnoreCase(stArr[j].stName)>0){
                        Student t = stArr[i];
                        stArr[i]=stArr[j];
                        stArr[j]=t;
                    }
                    
                }
                
            }
        }
    }//sortByNameASC
    public static void sortByNameDESC(){
        if (stArr !=null){
            for (int i = 0; i < stArr.length; i++) {
                for (int j = i+1; j < stArr.length; j++) {
                    if (stArr[i].stName.compareToIgnoreCase(stArr[j].stName)<0){
                        Student t = stArr[i];
                        stArr[i]=stArr[j];
                        stArr[j]=t;
                    }
                    
                }
                
            }
        }
    }//sortByStudentCodeDESC
    public static void sortByStudentCodeASC(){
        if (stArr !=null){
            for (int i = 0; i < stArr.length; i++) {
                for (int j = i+1; j < stArr.length; j++) {
                    if (stArr[i].stCode>stArr[j].stCode){
                        Student t = stArr[i];
                        stArr[i]=stArr[j];
                        stArr[j]=t;
                    }

                }

            }
        }
    }//sortByStudentCodeASC
    public static void sortByStudentCodeDESC(){
        if (stArr !=null){
            for (int i = 0; i < stArr.length; i++) {
                for (int j = i+1; j < stArr.length; j++) {
                    if (stArr[i].stCode<stArr[j].stCode){
                        Student t = stArr[i];
                        stArr[i]=stArr[j];
                        stArr[j]=t;
                    }

                }

            }
        }
    }//sortByStudentCodeDESC
    public static void sortByFieldASC(){
        if (stArr !=null){
            for (int i = 0; i < stArr.length; i++) {
                for (int j = i+1; j < stArr.length; j++) {
                    if (stArr[i].stField.compareToIgnoreCase(stArr[j].stField)>0){
                        Student t = stArr[i];
                        stArr[i]=stArr[j];
                        stArr[j]=t;
                    }

                }

            }
        }
    } //sortByFieldASC
    public static void sortByFieldDESC(){
        if (stArr !=null){
            for (int i = 0; i < stArr.length; i++) {
                for (int j = i+1; j < stArr.length; j++) {
                    if (stArr[i].stField.compareToIgnoreCase(stArr[j].stField)<0){
                        Student t = stArr[i];
                        stArr[i]=stArr[j];
                        stArr[j]=t;
                    }

                }

            }
        }
    } //sortByFieldDESC
    public static void sortByTotalGradeASC(){
        if (stArr !=null){
            for (int i = 0; i < stArr.length; i++) {
                for (int j = i+1; j < stArr.length; j++) {
                    if (stArr[i].totalGrade<stArr[j].totalGrade){
                        Student t = stArr[i];
                        stArr[i]=stArr[j];
                        stArr[j]=t;
                    }

                }

            }
        }
    }//sortByTotalGradeASC
    public static void sortByTotalGradeDESC(){
        if (stArr !=null){
            for (int i = 0; i < stArr.length; i++) {
                for (int j = i+1; j < stArr.length; j++) {
                    if (stArr[i].totalGrade<stArr[j].totalGrade){
                        Student t = stArr[i];
                        stArr[i]=stArr[j];
                        stArr[j]=t;
                    }

                }

            }
        }
    }//sortByTotalGradeDESC
}


//java is not my favord languch.
//hi hitler.
// clen code.
//my good and cool code.