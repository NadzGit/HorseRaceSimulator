public class TrackLogic {
    public static int laneNum = 1;
    public static String trackShape = "Straight Track";

    public void storeTrackInfo(int laneNum, String trackShape) {
        this.laneNum = laneNum;
        this.trackShape = trackShape;
        System.out.println("LANE NUMBER: " + laneNum);
        System.out.println("TRACK SHAPE: " + trackShape);
    }


}
