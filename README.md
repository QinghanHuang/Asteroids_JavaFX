COMP30820 Java Programming Project

Group 3

Please follow the steps below to build settings for game:

1. Add vm options (both for javafx and media as we add sound effect in the game):

      --module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml --add-modules javafx.controls,javafx.media
      
2. Mark the 'resources' Directory as Sources Root.

3. If you didn't got scores.txt file in 'resources' directory, you could use 'public void writeInitialScoresTest()' in Main Class to get a initial one.
