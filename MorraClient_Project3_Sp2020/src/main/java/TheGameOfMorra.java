/* 
 * Authors: Mallika Patil and Huy Truong
 * Description:  Games will be played until one of the
 * players has two points. At the end of each game, each user will be able to play again or
 * quit.
 */
import javafx.scene.control.Label;
import javafx.scene.text.Text; 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
public class TheGameOfMorra extends Application {

	ListView<String> listItems;
	Client clientConnection; 
	int p1Points = 0, p2Points = 0;
	int p1Plays = 0, p2Plays = 0;
	int p1Guess = 10, p2Guess = 10;
	boolean p1 = false, p2 = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Client) Let's Play Morra!!!");
		
		/* Images */ 
		Image bigLogo = new Image("logo.png", 100, 100, true, true); 
		Image smallLogo = new Image("logo.png", 35, 35, true, true); 
		Image connect = new Image("connect.png", 25, 25, true, true); 
		Image zeroFingers = new Image("zero-fingers.png", 50, 50, true, true); 
		Image oneFinger = new Image("one-finger.png", 50, 50, true, true); 
		Image twoFingers = new Image("two-fingers.png", 50, 50, true, true); 
		Image threeFingers = new Image("three-fingers.png", 50, 50, true, true); 
		Image fourFingers = new Image("four-fingers.png", 50, 50, true, true); 
		Image fiveFingers = new Image("five-fingers.png", 50, 50, true, true); 
		Image exit = new Image("exit.png", 25, 25, true, true); 
		Image plus = new Image("plus.png", 25, 25, true, true); 
		Image submit = new Image("submit.png", 25, 25, true, true);
		Image end = new Image("end.png", 25, 25, true, true); 
		Image replay = new Image("replay.png", 25, 25, true, true); 
		Image zero = new Image("zero.png", 35, 35, true, true); 
		Image one = new Image("one.png", 35, 35, true, true); 
		Image two = new Image("two.png", 35, 35, true, true); 
		Image three = new Image("three.png", 35, 35, true, true); 
		Image four = new Image("four.png", 35, 35, true, true); 
		Image five = new Image("five.png", 35, 35, true, true); 
		Image six = new Image("six.png", 35, 35, true, true); 
		Image seven = new Image("seven.png", 35, 35, true, true); 
		Image eight = new Image("eight.png", 35, 35, true, true); 
		Image nine = new Image("nine.png", 35, 35, true, true); 
		Image ten = new Image("ten.png", 35, 35, true, true); 
		
		/* Buttons */ 
		/* Hand Buttons */ 
		Button ZeroFingers = new Button("");
		ZeroFingers.setGraphic(new ImageView(zeroFingers));
		ZeroFingers.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #e1ef7e;"
        );
		
		Button OneFinger = new Button("");
		OneFinger.setGraphic(new ImageView(oneFinger));
		OneFinger.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #e1ef7e;"
        );
		
		Button TwoFingers = new Button("");
		TwoFingers.setGraphic(new ImageView(twoFingers));
		TwoFingers.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #e1ef7e;"
        );
		
		Button ThreeFingers = new Button("");
		ThreeFingers.setGraphic(new ImageView(threeFingers));
		ThreeFingers.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #e1ef7e;"
        );
		
		Button FourFingers = new Button("");
		FourFingers.setGraphic(new ImageView(fourFingers));
		FourFingers.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-base: #e1ef7e;"
        );
		
		Button FiveFingers = new Button("");
		FiveFingers.setGraphic(new ImageView(fiveFingers));
		FiveFingers.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #e1ef7e;"
        );
		
		/* Number Buttons */ 
		Button Zero = new Button("");
		Zero.setGraphic(new ImageView(zero));
		Zero.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button One = new Button("");
		One.setGraphic(new ImageView(one));
		One.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button Two = new Button("");
		Two.setGraphic(new ImageView(two));
		Two.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button Three = new Button("");
		Three.setGraphic(new ImageView(three));
		Three.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button Four = new Button("");
		Four.setGraphic(new ImageView(four));
		Four.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button Five = new Button("");
		Five.setGraphic(new ImageView(five));
		Five.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button Six = new Button("");
		Six.setGraphic(new ImageView(six));
		Six.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button Seven = new Button("");
		Seven.setGraphic(new ImageView(seven));
		Seven.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button Eight = new Button("");
		Eight.setGraphic(new ImageView(eight));
		Eight.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button Nine = new Button("");
		Nine.setGraphic(new ImageView(nine));
		Nine.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		Button Ten = new Button("");
		Ten.setGraphic(new ImageView(ten));
		Ten.setStyle(
				"-fx-min-width: 45px; " +
                "-fx-min-height: 45px; " +
                "-fx-max-width: 45px; " +
                "-fx-max-height: 45px;" +
                "-fx-base: #BBDEFB"
        );
		
		/* Functional Buttons */ 
		Button Connect = new Button("Connect");
		Connect.setGraphic(new ImageView(connect));
		Connect.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button Exit = new Button("");
		Exit.setGraphic(new ImageView(exit));
		Exit.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 35px; " +
                "-fx-min-height: 35px; " +
                "-fx-max-width: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button Plus = new Button("");
		Plus.setGraphic(new ImageView(plus));
		Plus.setStyle(
				"-fx-background-radius: 100em;" +
				"-fx-min-width: 35px; " +
                "-fx-min-height: 35px; " +
                "-fx-max-width: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button Submit = new Button("Submit");
		Submit.setGraphic(new ImageView(submit));
		Submit.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button End = new Button("End Game");
		End.setGraphic(new ImageView(end));
		End.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button ExitGame = new Button("Exit Game");
		ExitGame.setGraphic(new ImageView(exit));
		ExitGame.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		Button Replay = new Button("Restart Game");
		Replay.setGraphic(new ImageView(replay));
		Replay.setStyle(
				"-fx-background-radius: 100em;" +
                "-fx-min-height: 35px; " +
                "-fx-max-height: 35px;"
        );
		
		/* Text */ 
		Text ConnectionTitle = new Text("Establish Connection");
		Text ServerMessagesTitle = new Text("Server Messages:");
		Text OpponentGuessTitle = new Text("Opponent's Guess:");
		Text OpponentChoiceTitle = new Text("Opponent's Choice:");
		Text YourChoiceTitle = new Text("Your Choice:");
		Text YourGuessTitle = new Text("Your Guess:");
		Text ScoreBoardTitle = new Text("Score Board:");
		Text GameOverTitle = new Text("Game Over"); 
		
		/* Scene 1: User enter Port Number and IP Address */
		/* Connection Grid */ 
		GridPane ConnectionInfo = new GridPane(); 
		ConnectionInfo.setAlignment(Pos.BOTTOM_CENTER); 
		ConnectionInfo.setHgap(10); 
		ConnectionInfo.setVgap(10); 
		ConnectionInfo.setPadding(new Insets(25, 25, 25, 25)); 
		
		/* Connection Text */ 
		ConnectionInfo.setPadding(new Insets(0, 0, 0, 0)); 
        ConnectionInfo.add(ConnectionTitle, 0, 0, 2, 1); 
        
		Label IPAddress = new Label("IP Address :"); 
		ConnectionInfo.add(IPAddress, 0, 1); 
		
		TextField IP = new TextField(); 
		ConnectionInfo.add(IP, 1, 1);
		
		Label PortNumber = new Label("Port Number :");
		ConnectionInfo.add(PortNumber, 0, 2);
		
		TextField Port = new TextField(); 
		ConnectionInfo.add(Port, 1, 2);
		
		
		/* Connection Button */
		HBox Connection = new HBox(10);
		Connection.setAlignment(Pos.BOTTOM_RIGHT); 
		Connection.getChildren().add(Connect); 
		ConnectionInfo.add(Connection, 1, 4);
		
		HBox Logo = new HBox(10);
		Logo.setAlignment(Pos.BASELINE_CENTER);
		Logo.setPadding(new Insets(5, 0, 0, 0));
		Logo.getChildren().add(new ImageView(bigLogo));

		
		/* Scene 2: Game Scene */ 
		BorderPane GameLayout = new BorderPane();
		
		/* Game Header */
		HBox Header = new HBox(); 
		Header.setPadding(new Insets(5, 12, 5, 12));
		Header.setSpacing(10);
		Header.setStyle("-fx-background-color: #00bdaa");
		Header.setAlignment(Pos.BOTTOM_RIGHT);
		Header.getChildren().addAll(End, Plus, Submit, Exit, new ImageView(smallLogo));
		
		
		/* Server Messages */ 
		VBox ServerMessages = new VBox(); 
		ServerMessages.setPadding(new Insets(10));
		ServerMessages.setSpacing(8); 
		ServerMessages.setStyle("-fx-background-color: #d1cebd"); 
		
		ListView MessageBoard = new ListView();
		MessageBoard.setPrefWidth(150);
		MessageBoard.setPrefHeight(200);
		MessageBoard.setStyle("-fx-base: #ffffff"); 
		
		ServerMessages.getChildren().add(ServerMessagesTitle);

		
		/* Display Opponent Guess */
		Rectangle OpponentGuess = new Rectangle(100, 100, 100, 100); 
		OpponentGuess.setArcWidth(20); 
		OpponentGuess.setArcHeight(20); 
		OpponentGuess.setFill(Color.web("#BBDEFB"));
		StackPane OpponentGuessStack = new StackPane(); 
		OpponentGuessStack.getChildren().addAll(OpponentGuess, new ImageView(ten)); 
		
		/* Display Opponent Choice */
		Circle OpponentChoice = new Circle(); 
		OpponentChoice.setRadius(50);
		OpponentChoice.setFill(Color.web("#e1ef7e"));
		StackPane OpponentChoiceStack = new StackPane(); 
		OpponentChoiceStack.getChildren().addAll(OpponentChoice, new ImageView(fiveFingers)); 
		
		ServerMessages.getChildren().addAll(MessageBoard, OpponentChoiceTitle, OpponentChoiceStack, OpponentGuessTitle, OpponentGuessStack); 
		
		
		/* Game Player Choice */ 
		VBox GameChoices = new VBox(); 
		GameChoices.setPadding(new Insets(10));
		GameChoices.setSpacing(25); 
		GameChoices.setStyle("-fx-background-color: #f6eedf"); 
		
		HBox ChoiceSelection = new HBox(); 
		ChoiceSelection.setPadding(new Insets(15, 12, 15, 12));
		ChoiceSelection.setSpacing(10);
		ChoiceSelection.getChildren().addAll(ZeroFingers, OneFinger, TwoFingers, ThreeFingers, FourFingers, FiveFingers); 
		
		HBox GuessSelection = new HBox(); 
		GuessSelection.setPadding(new Insets(15, 12, 15, 12));
		GuessSelection.setSpacing(10);
		GuessSelection.getChildren().addAll(One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten); 
		
		Circle YourChoice = new Circle(); 
		YourChoice.setRadius(50);
		YourChoice.setFill(Color.web("#e1ef7e"));
		StackPane YourChoiceStack = new StackPane(); 
		YourChoiceStack.getChildren().addAll(YourChoice, new ImageView(zeroFingers)); 
		
		/* Your Choice Button Selection */ 
		ZeroFingers.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourChoiceStack.getChildren().clear();
				YourChoiceStack.getChildren().addAll(YourChoice, new ImageView(zeroFingers)); 
				if (p1)
					p1Plays = 0;
				else
					p2Plays = 0;
			}
		});
		
		OneFinger.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourChoiceStack.getChildren().clear();
				YourChoiceStack.getChildren().addAll(YourChoice, new ImageView(oneFinger));
				if (p1)
					p1Plays = 1;
				else
					p2Plays = 1;
			}
		});
		
		TwoFingers.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourChoiceStack.getChildren().clear();
				YourChoiceStack.getChildren().addAll(YourChoice, new ImageView(twoFingers));
				if (p1)
					p1Plays = 2;
				else
					p2Plays = 2;
			}
		});
		
		ThreeFingers.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourChoiceStack.getChildren().clear();
				YourChoiceStack.getChildren().addAll(YourChoice, new ImageView(threeFingers));
				if (p1)
					p1Plays = 3;
				else
					p2Plays = 3;
			}
		});
		
		FourFingers.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourChoiceStack.getChildren().clear();
				YourChoiceStack.getChildren().addAll(YourChoice, new ImageView(fourFingers));
				if (p1)
					p1Plays = 4;
				else
					p2Plays = 4;
			}
		});
		
		FiveFingers.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourChoiceStack.getChildren().clear();
				YourChoiceStack.getChildren().addAll(YourChoice, new ImageView(fiveFingers));
				if (p1)
					p1Plays = 5;
				else
					p2Plays = 5;
			}
		});
		
		/* Display Opponent Guess */
		Rectangle YourGuess = new Rectangle(100, 100, 100, 100); 
		YourGuess.setArcWidth(20); 
		YourGuess.setArcHeight(20); 
		YourGuess.setFill(Color.web("#BBDEFB"));
		StackPane YourGuessStack = new StackPane(); 
		YourGuessStack.getChildren().addAll(YourGuess, new ImageView(ten)); 
		
		/* Your Guess Button Selection */ 
		Zero.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(zero));
				if (p1)
					p1Guess = 0;
				else
					p2Guess = 0;
			}
		});
		
		One.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(one));
				if (p1)
					p1Guess = 1;
				else
					p2Guess = 1;
			}
		});
		
		Two.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(two));
				if (p1)
					p1Guess = 2;
				else
					p2Guess = 2;
			}
		});
		
		Three.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(three));
				if (p1)
					p1Guess = 3;
				else
					p2Guess = 3;
			}
		});
		
		Four.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(four));
				if (p1)
					p1Guess = 4;
				else
					p2Guess = 4;
			}
		});
		
		Five.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(five));
				if (p1)
					p1Guess = 5;
				else
					p2Guess = 5;
			}
		});
		
		Six.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(six));
				if (p1)
					p1Guess = 6;
				else
					p2Guess = 6;
			}
		});
		
		Seven.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(seven));
				if (p1)
					p1Guess = 7;
				else
					p2Guess = 7;
			}
		});
		
		Eight.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(eight));
				if (p1)
					p1Guess = 8;
				else
					p2Guess = 8;
			}
		});
		
		Nine.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(nine));
				if (p1)
					p1Guess = 9;
				else
					p2Guess = 9;
			}
		});
		
		Ten.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				YourGuessStack.getChildren().clear();
				YourGuessStack.getChildren().addAll(YourGuess, new ImageView(ten));
				if (p1)
					p1Guess = 10;
				else
					p2Guess = 10;
			}
		});
		
		/* Other Buttons */
		
		Exit.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Platform.exit(); 
			}
		});
		
		ExitGame.setOnAction( new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Platform.exit(); 
			}
		});
		
		GameChoices.getChildren().addAll(YourChoiceTitle, YourChoiceStack, ChoiceSelection, YourGuessTitle, YourGuessStack, GuessSelection); 
		
		/* Game Scores */ 
		VBox ScoreBoard = new VBox(); 
		ScoreBoard.setPadding(new Insets(10));
		ScoreBoard.setSpacing(8); 
		ScoreBoard.setPrefWidth(160); 
		ScoreBoard.setStyle("-fx-background-color: #f6eedf"); 
		
		ListView GameScore = new ListView();
		GameScore.setPrefWidth(50);
		GameScore.setPrefHeight(80);
		GameScore.setStyle("-fx-base: #fe346e"); 
		
		ScoreBoard.getChildren().addAll(ScoreBoardTitle, GameScore); 
		
		HBox GameChoicesScoreBoard = new HBox(GameChoices, ScoreBoard); 
		GameChoicesScoreBoard.setSpacing(0);
		GameLayout.setTop(Header);
		GameLayout.setLeft(ServerMessages);
		GameLayout.setCenter(GameChoicesScoreBoard);
		

		ScrollPane GameScrollPane = new ScrollPane();
		GameScrollPane.setContent(GameLayout); 
		GameScrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		GameScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		/* Scene 3: Game Ends */
		
		/* End Grid */ 
		
		GridPane GameOver = new GridPane(); 
		GameOver.setAlignment(Pos.BOTTOM_CENTER); 
		GameOver.setHgap(10); 
		GameOver.setVgap(10); 
		GameOver.setPadding(new Insets(25, 25, 25, 25)); 
		
		/* End Game Text */ 
		
		GameOver.setPadding(new Insets(0, 0, 0, 0)); 
        GameOver.add(GameOverTitle, 0, 0, 2, 1); 
        
		Label GameWinner = new Label("Game Winner :"); 
		GameOver.add(GameWinner, 0, 1); 
		
		TextField GameWinnerName  = new TextField(); 
		GameOver.add(GameWinnerName, 1, 1);
		
		Label PlayerOnePoints = new Label("Player 1 Points :");
		GameOver.add(PlayerOnePoints, 0, 2);
		
		
		Label PlayerTwoPoints = new Label("Your Points :");
		GameOver.add(PlayerTwoPoints, 0, 3);
		
		/* End Game Buttons */
		HBox GameOverOptions = new HBox(10);
		GameOverOptions.setAlignment(Pos.BOTTOM_CENTER); 
		GameOverOptions.getChildren().addAll(Replay, ExitGame); 
		GameOver.add(GameOverOptions, 1, 4);
		
		HBox EndLogo = new HBox(10);
		EndLogo.setAlignment(Pos.BASELINE_CENTER);
		EndLogo.setPadding(new Insets(5, 0, 0, 0));
		EndLogo.getChildren().add(new ImageView(bigLogo));
		
		Scene startScene = new Scene(new VBox(Logo, ConnectionInfo), 300, 275);
		Scene gameScene = new Scene(new VBox(GameLayout, GameScrollPane), 925, 600);
		Scene endScene = new Scene(new VBox(EndLogo, GameOver), 400, 300);
		
		Connect.setOnAction(e -> {

			clientConnection = new Client ( data -> {
				Platform.runLater(() -> {

					int gameMode = data.gameMode;
					System.out.println(gameMode);
					primaryStage.setScene(gameScene);

					if (gameMode == -999)
						MessageBoard.getItems().add("The other client's connection has dropped!");
					// game mode of 2 from the in stream meaning its player classification time
					else if (gameMode == 2) {
						p1 = data.p1;
						p2 = data.p2;
					}
					// game mode of 3 from the in stream meaning its player judging time
					else if (gameMode == 3) {
						if (p1 == true) {

							MessageBoard.getItems().add("I am player 1!");
							MessageBoard.getItems().add("Player 2 plays " + data.p2Plays);
							MessageBoard.getItems().add("Player 2 guess " + data.p2Guess);

						}
						else if (p2 == true) {

							MessageBoard.getItems().add("I am player 2!");
							MessageBoard.getItems().add("Player 1 plays " + data.p1Plays);
							MessageBoard.getItems().add("Player 1 guess " + data.p2Guess);

						}
						GameScore.getItems().add("Score Summary:");
						GameScore.getItems().add("Player 1 : " + data.p1Points);
						GameScore.getItems().add("Player 2 : " + data.p2Points);
					}

				}); }, IP.getText(), Integer.parseInt(Port.getText()));

			clientConnection.start();


		});

		Submit.setOnAction(e -> {

			// game mode being 3 on client side meaning the client is sending this information to the server
			clientConnection.send(new MorraInfo(3, p1Points, p2Points, p1Plays, p2Plays, p1Guess, p2Guess, p1, p2));
			listItems.getItems().add("Submit successfully!");

		});
		

		TextField  PlayerOnePointsNumber = new TextField(Integer.toString(p1Points)); 
		GameOver.add(PlayerOnePointsNumber, 1, 2);
		

		TextField PlayerTwoPointsNumber = new TextField(Integer.toString(p2Points)); 
		GameOver.add(PlayerTwoPointsNumber, 1, 3);
		
		End.setOnAction(e-> primaryStage.setScene(endScene));

		Replay.setOnAction(e -> {
			primaryStage.setScene(startScene);
			primaryStage.show();

		});
		primaryStage.setScene(startScene);
		primaryStage.show();
	}

}