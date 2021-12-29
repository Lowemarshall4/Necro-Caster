/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Marshall
 */
public class GameMain {

    
    
    
    
    public static void main(String args[]) {
        GameView view = new GameView();
        GameModel model = new GameModel();
        Controller controller = new Controller(view, model);
        controller.beginGame();

        
    }
}
