import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{

        System.out.println("Welcome to Backpack");
        System.out.println("1. Enter as instructor");
        System.out.println("2. Enter as student\n" +
                "3. Exit");

        reader.init(System.in);
        int n = reader.nextInt();

        // Create ArrayList of Slides.
        ArrayList<slides> slidesArrayList = new ArrayList<>();

        //ArrayList of videos.
        ArrayList<Video> videoArrayList = new ArrayList<>();

        // ArrayList of Assignments.
        ArrayList<Assignment> assignmentArrayList = new ArrayList<>();

        // ArrayList for quizzes
        ArrayList<quiz> quizArrayList = new ArrayList<>();

        /*
        // Hashmap is used to map assignments and ary of student ids of which students have done that particular assignment.
        HashMap<Assignment, ArrayList> assignmentArrayListHashMap = new HashMap<>();
        HashMap<quiz,ArrayList> quizArrayListHashMap = new HashMap<>();
         */

        // ArrayList of students submissions.
        ArrayList<stdntssubmtd> stdntssubmtdArrayList = new ArrayList<>();
        //initialising stdntsubmtedArrayList with empty submissions to use it further.
        for (int i = 0; i < 2; i++) { // As we are considering 3 students.
            stdntssubmtd std = new stdntssubmtd();
            std.setStdID(i);
            std.setNoOfquizsubmtd(0);
            std.setNoOfAsgnmtssubtmd(0);
            stdntssubmtdArrayList.add(std);
        }

        while(n!=3){
            if (n==1){
                System.out.print(0+" - I0\n" + 1+" - I1\n"+"Choose id: ");
                int insId = reader.nextInt();
                System.out.println("Welcome I"+insId);

                // menu class shows the menu of student and instructors
                menu insMenu = new menu();
                insMenu.showInsMenu();

                int menuOption = reader.nextInt();

                while(menuOption!=9){

                    if (menuOption==1){
                        System.out.println("1. Add Lecture Slide\n" +
                                "2. Add Lecture Video");
                        int lecOption = reader.nextInt();

                        if (lecOption==1){

                            System.out.println("Enter topic of slides: ");
                            String slidTopic = reader.next();

                            System.out.println("Enter number of slides: ");
                            int numOfSlides = reader.nextInt();

                            String[] contentAry = new String[numOfSlides];
                            for (int i = 0; i < numOfSlides; i++) {
                                contentAry[i] = reader.next();
                            }

                            // prepare new slide class and add it in the slide ArrayList.
                            slides sld = new slides();
                            sld.setTopicOfSlides(slidTopic);
                            sld.setNumberOfSlides(numOfSlides);
                            sld.setContentAry(contentAry);
                            sld.setUploadtime(getTime());
                            sld.setuploaded("I"+insId);

                            slidesArrayList.add(sld);
                        }

                        else if (menuOption==2){
                            System.out.println("Enter topic of video: ");
                            String videoTopic = reader.next();

                            System.out.println("Enter filename of video: ");
                            String videoFileName = reader.next();

                            if (videoFileName.endsWith(".mp4")){

                                // Create video object and add in video ArrayList.

                                Video vid = new Video();
                                vid.setTopicOfVideo(videoTopic);
                                vid.setVideoFileName(videoFileName);
                                vid.setUploadtime(getTime());
                                vid.setuploaded("I"+insId);

                                videoArrayList.add(vid);
                            }
                            else{
                                System.out.println("please enter a valid video file name and start again");
                            }
                        }
                    }

                    else if (menuOption==2){

                        System.out.println("1. Add Assignment\n" +
                                "2. Add Quiz");

                        int typeOfAssessment = reader.nextInt();

                        if (typeOfAssessment==1){
                            System.out.print("Enter problem statement: ");
                            String prblmStatement = reader.nextLine();

                            System.out.print("Enter max marks: ");
                            int maxMarks = reader.nextInt();

                            // Create assignment object and add in assignment ArrayList.
                            Assignment assignment = new Assignment();
                            assignment.setPrblmStatement(prblmStatement);
                            assignment.setMaxMarks(maxMarks);
                            assignment.setUploadtime(getTime());
                            assignment.setuploaded("I"+insId);

                            assignmentArrayList.add(assignment);
                        }
                        else if (typeOfAssessment==2){
                            System.out.println("Enter quiz question: ");
                            String quizQuestion = reader.nextLine();

                            quiz qz = new quiz();
                            qz.setQuizQuestion(quizQuestion);
                            qz.setUploadtime(getTime());
                            qz.setuploaded("I"+insId);

                            quizArrayList.add(qz);
                        }
                    }
                    
                    else if (menuOption==3){

                        // Prints slides
                        for (int i = 0; i < slidesArrayList.size(); i++) {
                            System.out.println("Title: "+slidesArrayList.get(i).getTopicOfSlides());
                            for (int j = 0; j < slidesArrayList.get(i).getContentAry().length; j++) {
                                int temp = j+1;
                                System.out.println("Slide "+temp+": "+slidesArrayList.get(i).getContentAry()[j]);
                            }
                            System.out.println("Number of slides: "+slidesArrayList.get(i).getContentAry().length);
                            System.out.println("Date of upload: " + slidesArrayList.get(i).getUploadtime());
                            System.out.println("Uploaded by: "+ slidesArrayList.get(i).getuploadedby());
                        }

                        // Prints lecture vidoes.

                        for (int i = 0; i <videoArrayList.size(); i++) {
                            System.out.println("Title of video: " + videoArrayList.get(i).getTopicOfVideo());
                            System.out.println("Video file: "+videoArrayList.get(i).getVideoFileName());
                            System.out.println("Date of upload: " + videoArrayList.get(i).getUploadtime());
                            System.out.println("Uploaded by: " + videoArrayList.get(i).getuploadedby());
                        }
                    }

                    else if (menuOption==4){
                        // View Assessments.

                        int idOfAssessments = 0;

                        for (int i = 0; i < assignmentArrayList.size(); i++) {
                            System.out.println("ID: "+ (idOfAssessments++) + "Assignment: "+ assignmentArrayList.get(i).getPrblmStatement()+" Max Marks: "+assignmentArrayList.get(i).getMaxMarks());
                        }

                        for (int i = 0; i < quizArrayList.size(); i++) {
                            System.out.println("ID: "+ (idOfAssessments++) + "Question: " + quizArrayList.get(i).getQuizQuestion());
                        }
                    }

                    else if (menuOption==5){

                    }
                    insMenu.showInsMenu();
                    menuOption = reader.nextInt();
                }
            }

            else if (n==2){

                System.out.print("Students:\n" +
                        "0 - S0\n" +
                        "1 - S1\n" +
                        "2 - S2\n" + "Choose id: " );

                int studentId = reader.nextInt();

                System.out.println("Welcome " + "S"+studentId);

                menu mn = new menu();
                mn.showStuMenu();

                int menOption = reader.nextInt();

                while(menOption!=7){

                    if (menOption==1){
                        // Prints slides
                        for (int i = 0; i < slidesArrayList.size(); i++) {
                            System.out.println("Title: "+slidesArrayList.get(i).getTopicOfSlides());
                            for (int j = 0; j < slidesArrayList.get(i).getContentAry().length; j++) {
                                int temp = j+1;
                                System.out.println("Slide "+temp+": "+slidesArrayList.get(i).getContentAry()[j]);
                            }
                            System.out.println("Number of slides: "+slidesArrayList.get(i).getContentAry().length);
                            System.out.println("Date of upload: " + slidesArrayList.get(i).getUploadtime());
                            System.out.println("Uploaded by: "+ slidesArrayList.get(i).getuploadedby());
                        }

                        // Prints lecture vidoes.

                        for (int i = 0; i <videoArrayList.size(); i++) {
                            System.out.println("Title of video: " + videoArrayList.get(i).getTopicOfVideo());
                            System.out.println("Video file: "+videoArrayList.get(i).getVideoFileName());
                            System.out.println("Date of upload: " + videoArrayList.get(i).getUploadtime());
                            System.out.println("Uploaded by: " + videoArrayList.get(i).getuploadedby());
                        }
                    }

                    else if (menOption==2){
                        // View Assessments.

                        int idOfAssessments = 0;

                        for (int i = 0; i < assignmentArrayList.size(); i++) {
                            System.out.println("ID: "+ (idOfAssessments++) + "Assignment: "+ assignmentArrayList.get(i).getPrblmStatement()+" Max Marks: "+assignmentArrayList.get(i).getMaxMarks());
                        }

                        for (int i = 0; i < quizArrayList.size(); i++) {
                            System.out.println("ID: "+ (idOfAssessments++) + "Question: " + quizArrayList.get(i).getQuizQuestion());
                        }
                    }

                    else if (menOption==3){
                        // Submit assessment.

                        System.out.println("Pending assessments");
                        int stdIdInStdSubmted = 0;
                        //finding out how many assessments student didn't attempt.
                        for (int j = 0; j < stdntssubmtdArrayList.size(); j++) {
                            if (stdntssubmtdArrayList.get(j).getStdID() == studentId){
                                stdIdInStdSubmted = j;
                            }
                        }

                        int ID = 0;
                        HashMap<Integer,Assignment> assignmentIDHashMap = new HashMap<>();
                        HashMap<Integer,quiz> quizIDHashMap = new HashMap<>();


                        if (stdntssubmtdArrayList.get(stdIdInStdSubmted).getNoOfAsgnmtssubtmd()+stdntssubmtdArrayList.get(stdIdInStdSubmted).getNoOfquizsubmtd() == 0){
                            for (int i = 0; i < assignmentArrayList.size(); i++) {
                                int temp = ID++;
                                System.out.println("ID: "+temp+"Assignment: "+assignmentArrayList.get(i).getPrblmStatement()+"Max Marks: "+assignmentArrayList.get(i).getMaxMarks());
                                assignmentIDHashMap.put(temp,assignmentArrayList.get(i));
                            }

                            for (int i = 0; i < quizArrayList.size(); i++) {
                                int temp = ID++;
                                System.out.println("ID: "+temp+"Question: "+quizArrayList.get(i).getQuizQuestion());
                                quizIDHashMap.put(temp,quizArrayList.get(i));
                            }
                        }
                        System.out.print("Enter ID of assessment: ");
                        int IdofAssesment = reader.nextInt();
                        if (IdofAssesment>=assignmentArrayList.size()){
                            System.out.print(quizIDHashMap.get(IdofAssesment).getQuizQuestion());
                            String ansOfQuizQuestion = reader.next();
                        }
                        else {
                            System.out.print("Enter filename of assignment: ");
                            String filenameOfAssignment = reader.next();
                            if (filenameOfAssignment.endsWith(".zip")){

                            }
                        }
                        for (int i = 0; i < assignmentArrayList.size(); i++) {

                        }

                        for (int i = 0; i < quizArrayList.size(); i++) {

                        }

                    }
                    mn.showStuMenu();

                    menOption = reader.nextInt();
                }
            }
            System.out.println("Welcome to Backpack");
            System.out.println("1. Enter as instructor");
            System.out.println("2. Enter as student\n" +
                    "3. Exit");
            n = reader.nextInt();
        }

        System.out.println("-------------------------------------------------------------------------------------------------\n" + "{End of Test Case}");
        return;
    }
    
    // Function which returns time.
    public static String getTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String s = formatter.format(date);
        return s;
    }
}

class stdntssubmtd{

    private int stdID;
    private int noOfAsgnmtssubtmd;
    private int noOfquizsubmtd;
    private ArrayList<Assignment> assignmentsSubmtd;
    private ArrayList<quiz> quizzesSubmtd;

    public int getNoOfAsgnmtssubtmd() {
        return noOfAsgnmtssubtmd;
    }

    public void setNoOfAsgnmtssubtmd(int noOfAsgnmtssubtmd) {
        this.noOfAsgnmtssubtmd = noOfAsgnmtssubtmd;
    }

    public int getNoOfquizsubmtd() {
        return noOfquizsubmtd;
    }

    public void setNoOfquizsubmtd(int noOfquizsubmtd) {
        this.noOfquizsubmtd = noOfquizsubmtd;
    }

    public ArrayList<quiz> getQuizzesSubmtd() {
        return quizzesSubmtd;
    }

    public void setQuizzesSubmtd(ArrayList<quiz> quizzesSubmtd) {
        this.quizzesSubmtd = quizzesSubmtd;
    }

    public ArrayList<Assignment> getAssignmentsSubmtd() {
        return assignmentsSubmtd;
    }

    public void setAssignmentsSubmtd(ArrayList<Assignment> assignmentsSubmtd) {
        this.assignmentsSubmtd = assignmentsSubmtd;
    }

    public int getStdID() {
        return stdID;
    }

    public void setStdID(int stdID) {
        this.stdID = stdID;
    }
}

interface UploadDetails{
    void setuploaded(String Id);
    String getuploadedby();
    String getUploadtime();
    void setUploadtime(String uploadtime);
}

class quiz implements UploadDetails{
    private String quizQuestion;
    private String uploadedby;
    private String uploadtime;

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    @Override
    public void setuploaded(String Id) {
        this.uploadedby = Id;
    }
    public String getuploadedby(){
        return uploadedby;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }
}

class Assignment implements UploadDetails{

    private String prblmStatement;
    private int maxMarks;
    private String uploadedby;
    private String uploadtime;

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    @Override
    public void setuploaded(String Id) {
        this.uploadedby = Id;
    }
    public String getuploadedby(){
        return uploadedby;
    }

    public String getPrblmStatement() {
        return prblmStatement;
    }

    public void setPrblmStatement(String prblmStatement) {
        this.prblmStatement = prblmStatement;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }
}

class Video implements UploadDetails{
    //encapsulation used.

    private String topicOfVideo;
    private String VideoFileName;
    private String uploadedby;
    private String uploadtime;

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    @Override
    public void setuploaded(String Id) {
        this.uploadedby = Id;
    }
    public String getuploadedby(){
        return uploadedby;
    }

    public String getTopicOfVideo() {
        return topicOfVideo;
    }

    public void setTopicOfVideo(String topicOfVideo) {
        this.topicOfVideo = topicOfVideo;
    }

    public String getVideoFileName() {
        return VideoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        VideoFileName = videoFileName;
    }

}

class slides implements UploadDetails{
    //encapsulation used.

    private int numberOfSlides;
    private String[] contentAry;
    private String topicOfSlides;
    private String uploadedby;
    private String uploadtime;

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    @Override
    public void setuploaded(String Id) {
        this.uploadedby = Id;
    }
    public String getuploadedby(){
        return uploadedby;
    }

    public int getNumberOfSlides() {
        return numberOfSlides;
    }

    public void setNumberOfSlides(int numberOfSlides) {
        this.numberOfSlides = numberOfSlides;
    }

    public String getTopicOfSlides() {
        return topicOfSlides;
    }

    public void setTopicOfSlides(String topicOfSlides) {
        this.topicOfSlides = topicOfSlides;
    }

    public String[] getContentAry() {
        return contentAry;
    }

    public void setContentAry(String[] contentAry) {
        this.contentAry = contentAry;
    }
}

class menu{
    public void showInsMenu(){
        System.out.println("INSTRUCTOR MENU\n" +
                "1. Add class material\n" +
                "2. Add assessments\n" +
                "3. View lecture materials\n" +
                "4. View assessments\n" +
                "5. Grade assessments\n" +
                "6. Close assessment\n" +
                "7. View comments\n" +
                "8. Add comments\n" +
                "9. Logout");
    }
    public void showStuMenu(){
        System.out.println("STUDENT MENU\n" +
                "1. View lecture materials\n" +
                "2. View assessments\n" +
                "3. Submit assessment\n" +
                "4. View grades\n" +
                "5. View comments\n" +
                "6. Add comments\n" +
                "7. Logout");

    }
}

//general reader class.
class reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    // call this method to initialize reader for InputStream
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    // get next word
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
        return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    static long nextLong() throws IOException{
        return Long.parseLong(next());
    }
}
