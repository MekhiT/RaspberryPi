public class TrackController {
    Block[] Blocks;
    int Switch_ID, Branch1, Branch2;
    public TrackController(int numBlocks) {
        Blocks = new Block[numBlocks];
        for (int i = 0; i < numBlocks; i++) {
            Blocks[i].length = 10;
            Blocks[i].max_speed = 40;
            Blocks[i].ID = i;
            Blocks[i].occupancy = false;
        }
        Blocks[3].station = true;
    }
    public TrackController( int numBlocks, int ID_Switch, int Branch1, int Branch2){
            Blocks = new Block[numBlocks];
            for (int i = 0; i < numBlocks; i++) {
                Blocks[i].length = 10;
                Blocks[i].max_speed = 40;
                Blocks[i].ID = i;
                Blocks[i].occupancy = false;
            }
            Blocks[3].station = true;
            Switch_ID = ID_Switch;
            Blocks[ID_Switch].has_switch = true;
            this.Branch1 = Branch1;
            this.Branch2 = Branch2;
        }
        public boolean getOccupancy ( int ID){
            return Blocks[ID].occupancy;
        }
        public boolean getAuthority ( int ID){
            for (int i = ID; i < Blocks.length; i++) {
                if (Blocks[i].occupancy) return false;
            }
            return true;
        }
        public void toggle_switch () {
            Blocks[Switch_ID].switch_position = !Blocks[Switch_ID].switch_position;
        }
        private class Block {
            int length, max_speed, ID;
            boolean station, occupancy, has_switch, switch_position;
        }
}
