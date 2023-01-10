package javafx;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class javafx extends Application {
private static boolean esc = false;
private static Scene scene;
private static int y=0, x=0, z=0;


  private static boolean wt = false, at = false, st = false, dt = false;
 private static double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        private static double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
private static Group root, cc;
private static Group outroot, circ1;
ArrayList<String> boxpos= new ArrayList<>();
Circle cu1 = new Circle(20,20,1);
int blocks = 0;
Image image12 = new Image(getClass().getResourceAsStream("cb.png"));
ImageView cu = new ImageView(image12);
    private static PerspectiveCamera camera;
  private static Rotate rotation, rotation2, rotation3;

    public static void main(String[] args) {
        launch(args);
    }
   
 public void cb(String type, final int x, final int y, final int z, int bn){
  
 final Rectangle cube1 = new Rectangle(40,40),cube2 = new Rectangle(40,40),cube3 = new Rectangle(40,40),
        cube4 = new Rectangle(40,40),cube5 = new Rectangle(40,40),cube6 = new Rectangle(40,40);  
  final Group cubes = new Group(cube1,cube2,cube3,cube4,cube5,cube6);
   cube1.setSmooth(false);

boxpos.add(x + " " + y + " " + z);
        cube2.setSmooth(false);
        cube3.setSmooth(false);
        cube4.setSmooth(false);
        cube5.setSmooth(false);
    if("dirt".equals(type)){
Image image = new Image(getClass().getResourceAsStream("sidedirt.png"));


ImagePattern imagePattern = new ImagePattern(image);
Image image1 = new Image(getClass().getResourceAsStream("topdirt.png"));


ImagePattern imagePattern1 = new ImagePattern(image1);
Image image11 = new Image(getClass().getResourceAsStream("botdirt.jpg"));


ImagePattern imagePattern11 = new ImagePattern(image11);
cube4.setFill(imagePattern1);
cube3.setFill(imagePattern11);
         cube1.setFill(imagePattern);
         cube2.setFill(imagePattern);
          cube5.setFill(imagePattern);
         cube6.setFill(imagePattern);
    }
      cube1.setTranslateX(0.0);
      cube1.setTranslateZ(0.0);
      cubes.setTranslateY((40.0*y)-40);
      cubes.setTranslateX((40.0*x)-40);
      cubes.setTranslateZ((40.0*z)-40);
     
     cube2.setTranslateZ(-40);
  cube3.setRotationAxis(Rotate.X_AXIS);
  cube3.setRotate(90);
  cube3.setTranslateY(20);
  cube3.setTranslateZ(-20);
    cube4.setRotationAxis(Rotate.X_AXIS);
  cube4.setRotate(90);
  cube4.setTranslateY(-20);
  cube4.setTranslateZ(-20);
  cube4.setTranslateX(-0);
  cube5.setRotationAxis(Rotate.Y_AXIS);
  cube5.setTranslateX(-20);
  cube5.setTranslateY(0);
    cube5.setTranslateZ(-20);
  cube5.setRotate(90);
    cube6.setRotationAxis(Rotate.Y_AXIS);
    cube6.setTranslateY(0);
  cube6.setTranslateX(20);
    cube6.setTranslateZ(-20);
  cube6.setRotate(90);
  cube6.setSmooth(false);
       cc.getChildren().add(cubes);



final int bn2 = bn;

             final int xe = x;
 final int ye = y;
final int ze = z;
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    Runnable toRun = new Runnable() {
        @Override
        public void run() {
                if(boxpos.contains(xe + " " + ye + " " + ze)){
                    // facing east
           if(boxpos.contains(xe+1 + " " + ye + " " + ze)){
              
               cube6.setOpacity(0);
            }else{
               cube6.setOpacity(1);
           }
           // facing west
             if(boxpos.contains(xe-1 + " " + ye + " " + ze)){
               cube5.setOpacity(0);
            }else{
               cube5.setOpacity(1);
           }
             // facing south
              if(boxpos.contains(xe + " " + ye + " " + (ze+1))){
               cube1.setOpacity(0);
            }else{
               cube1.setOpacity(1);
           }
              // facing north
               if(boxpos.contains(xe + " " + ye + " " + (ze-1))){
               cube2.setOpacity(0);
            }else{
               cube2.setOpacity(1);
           }
            }
        }
    };
ScheduledFuture<?> handle = scheduler.scheduleAtFixedRate(toRun, 1, 30, TimeUnit.MILLISECONDS);



cube1.setOnMousePressed(event -> {
  if (event.getButton() == MouseButton.PRIMARY){
      final Point3D boxPosition = cube1.localToScene(Point3D.ZERO);
            final Point3D cameraPosition = new Point3D(0,0,0);
     
            double distance = boxPosition.distance(cameraPosition);
           
            if(distance<250){

                    blocks = blocks + 1;
           cb(type, xe, ye, ze+1,blocks);
            }}
  if (event.getButton() == MouseButton.SECONDARY) {
  cc.getChildren().remove(cubes);
   boxpos.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String name) {
                if (name.equals(xe + " " + ye + " " + ze)) {
                    return "null" ;
                }else{
                    return name;
                }
                
            }
        });
   blocks = blocks-1;
  }
        });
 

cube2.setOnMousePressed(event -> {
  if (event.getButton() == MouseButton.PRIMARY){
      final Point3D boxPosition1 = cube2.localToScene(Point3D.ZERO);
            final Point3D cameraPosition1 = new Point3D(0,0,0);
     
            double distance = boxPosition1.distance(cameraPosition1);
           
            if(distance<250){
  
                    blocks = blocks + 1;
           cb(type, xe, ye, ze-1,blocks);
            }}
   if (event.getButton() == MouseButton.SECONDARY) {
  cc.getChildren().remove(cubes);   
   boxpos.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String name) {
                if (name.equals(xe + " " + ye + " " + ze)) {
                    return "null" ;
                }else{
                    return name;
                }
                
            }
        });
  blocks = blocks-1;
  }
        });


cube3.setOnMousePressed(event -> {
  if (event.getButton() == MouseButton.PRIMARY){
      
      final Point3D boxPosition11 = cube3.localToScene(Point3D.ZERO);
            final Point3D cameraPosition11 = new Point3D(0,0,0);
            double distance = boxPosition11.distance(cameraPosition11);
           
            if(distance<250){
  
                    blocks = blocks + 1;
           cb(type, xe, ye+1, ze,blocks);
            }}
   if (event.getButton() == MouseButton.SECONDARY) {
  cc.getChildren().remove(cubes);
 boxpos.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String name) {
                if (name.equals(xe + " " + ye + " " + ze)) {
                    return "null" ;
                }else{
                    return name;
                }
                
            }
        });
   blocks = blocks-1;
  }
        });
 

cube4.setOnMousePressed(event -> {
  if (event.getButton() == MouseButton.PRIMARY){
      
     final Point3D boxPosition111 = cube4.localToScene(Point3D.ZERO);
            final Point3D cameraPosition111 = new Point3D(0,0,0);
            double distance = boxPosition111.distance(cameraPosition111);
           
            if(distance<250){
  
                    blocks = blocks + 1;
           cb(type, xe, ye-1, ze,blocks);
            }}
   if (event.getButton() == MouseButton.SECONDARY) {
  cc.getChildren().remove(cubes);
 boxpos.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String name) {
                if (name.equals(xe + " " + ye + " " + ze)) {
                    return "null" ;
                }else{
                    return name;
                }
                
            }
        });
   blocks = blocks-1;
  }
        });


cube5.setOnMousePressed(event -> {
  if (event.getButton() == MouseButton.PRIMARY){
       final Point3D boxPosition1111 = cube5.localToScene(Point3D.ZERO);
            final Point3D cameraPosition1111 = new Point3D(0,0,0);
     
            double distance = boxPosition1111.distance(cameraPosition1111);
           
            if(distance<250){
  
                    blocks = blocks + 1;
           cb(type, xe-1, ye, ze,blocks);
            }}
   if (event.getButton() == MouseButton.SECONDARY) {
  cc.getChildren().remove(cubes);
   boxpos.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String name) {
                if (name.equals(xe + " " + ye + " " + ze)) {
                    return "null" ;
                }else{
                    return name;
                }
                
            }
        });
   blocks = blocks-1;
  }
        });
 
cube6.setOnMousePressed(event -> {
  if (event.getButton() == MouseButton.PRIMARY){
      final Point3D boxPosition11111 = cube6.localToScene(Point3D.ZERO);
            final Point3D cameraPosition11111 = new Point3D(0,0,0);

     
            double distance = boxPosition11111.distance(cameraPosition11111);
           
            if(distance<250){
  
                    blocks = blocks + 1;
           cb(type, xe+1, ye, ze,blocks);}
            }
   if (event.getButton() == MouseButton.SECONDARY) {
  cc.getChildren().remove(cubes);
  boxpos.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String name) {
                if (name.equals(xe + " " + ye + " " + ze)) {
                    return "null" ;
                }else{
                    return name;
                }
                
            }
        });
   blocks = blocks-1;
  
  }
        });
 }
    @Override
    public void start(Stage stage) throws Exception {
      
      
     
     Robot bot;
                try {
                   
                   
                        
                    bot = new Robot();
                     
         

                        bot.mouseMove((int) screenWidth/2, (int) screenHeight/2);
                     
                
                     
                } catch (AWTException ex) {
                   
                }
     
  
   
                
      PhongMaterial material = new PhongMaterial();
      material.setDiffuseColor(Color.BROWN);

 
        
 
  
           PhongMaterial materiall = new PhongMaterial();

materiall.setDiffuseMap(new Image(getClass().getResourceAsStream("mcdirt.png")));

        camera = new PerspectiveCamera( true );
        camera.setTranslateX( 0 );
        camera.setTranslateY( 0 );
   camera.setScaleX(1);
 camera.setScaleY(1);
 camera.setScaleZ(1);

        camera.setTranslateZ( 0 );
    
        camera.setFarClip(500.0);
        camera.setNearClip(0.01);
        camera.setFieldOfView(60);
     
stage.setOnHiding( event -> { System.exit(0); } );
        PointLight light = new PointLight();
     
        light.setTranslateX( 0 );
        light.setTranslateY( 0 );
        light.setTranslateZ( 0 );




 cu.setSmooth(false);

     cc = new Group(light);
        root = new Group(cc);
        cu.setScaleX(0.00001);
         cu.setScaleY(0.00001);

     
         cu.setScaleZ(1);
          cu1.setScaleX(0.1);
        cu1.setScaleY(0.1);
         cu1.setScaleZ(1);
             cu.setTranslateX(-185);
         cu1.setTranslateX(20);
         cu.setTranslateY(-185);
         cu1.setTranslateY(150);
          cu.setTranslateZ(1);
          cu1.setTranslateZ(-1);
        outroot = new Group(root);
        
      circ1 = new Group(cu, cu1);
     
     
       Group e123 = new Group(outroot, circ1);
        scene = new Scene( e123, 800, 600, true);
        stage.setFullScreen(true);
        scene.setOnMouseMoved( new MouseLook() );
        scene.setFill(Color.SKYBLUE);
   
        scene.setCamera( camera );
       
   
 
        stage.setScene(scene);
    
        stage.setTitle("Mouselook rotation");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    Runnable toRun = new Runnable() {
        public void run() {
             if(camera.intersects(root.getBoundsInLocal())) {
      System.out.println("bn2");
    }
            if(wt){
               int tx = (int) (root.getTranslateZ() - (2));
          root.setTranslateZ(tx); 
            }
            if(at){
               int tx = (int) (root.getTranslateX() + (2));
          root.setTranslateX(tx); 
            }
            if(st){
            int tx = (int) (root.getTranslateZ() + (2));
          root.setTranslateZ(tx);
            }
            if(dt){
                int tx = (int) (root.getTranslateX() - (2));
          root.setTranslateX(tx);
        
            }
            
        }
    };
ScheduledFuture<?> handle = scheduler.scheduleAtFixedRate(toRun, 1, 10, TimeUnit.MILLISECONDS);
          scene.setOnKeyPressed(e -> {
              

         if (e.getCode() == KeyCode.ESCAPE) {
             esc = true;
             scene.setCursor(Cursor.DEFAULT);
    
             System.exit(0);
         }
      
       
    
       
         if (e.getCode() == KeyCode.W) {
            
        wt = true;
    }
        
           
             if (e.getCode() == KeyCode.S) {
st = true;
             }
            
            
                     if (e.getCode() == KeyCode.A) {
    at = true;
    }
                 
               
                                  if (e.getCode() == KeyCode.D) {
     dt = true;
    }
    
          
           });
          scene.setOnKeyReleased(e -> {
 
         if (e.getCode() == KeyCode.W) {
        wt = false;
    }
        
           
             if (e.getCode() == KeyCode.S) {
st = false;
             }
            
            
                     if (e.getCode() == KeyCode.A) {
    at = false;
    }
                 
               
                                  if (e.getCode() == KeyCode.D) {
     dt = false;
    }
    
          
           });
        stage.show();
         for(int i2 = 0; i2 < 8; ++i2){
        for(int i = 0; i < 8; ++i){
    
        blocks = blocks+1;
cb("dirt", i2,3,i,blocks);
        }}

    }

    static class MouseLook implements EventHandler <MouseEvent> {
      
        private static int oldX, newX;
 
        
        private static int oldY, newY, weez;
        private static boolean alreadyMoved = false;
     
      public void mouseset(){
           Robot bot;
                try {
                     if(!esc){
                   
                        
                    bot = new Robot();
                     
         
   
                        bot.mouseMove((int) (screenWidth/2)+5, (int) (screenHeight/2)+5);
                     
                   weez = 0;
                     }
                } catch (AWTException ex) {
                   
                }
}
        @Override
        public void handle(MouseEvent event) {
              
            
            if ( alreadyMoved ) {
          
    
        
             
             Point3D pivot = new Point3D(0, 0, 0);
         
                  Point3D localPivot = root.parentToLocal(pivot);
                newX = (int) event.getScreenX();
                newY = (int) event.getScreenY();
                   if (newX < (screenWidth/2)-30||newX > (screenWidth/2)+30||newY < (screenHeight/2)-30||newY > (screenHeight/2)+30){
                       mouseset(); 
                   }
                if ( oldX < newX ) { 
                   
              
                   
                 
                    rotation = new Rotate( -3, localPivot.getX(), localPivot.getY(), localPivot.getZ(),
                            
                           
                            Rotate.Y_AXIS );
                
                  
                   root.getTransforms().addAll( rotation);
                  
              
                   
y=y-3;
if(y<-3){
    y = 357;
}

                    
                } else if ( oldX > newX ) { 
                       
               
                      
                    rotation = new Rotate( +3,localPivot.getX(), localPivot.getY(), localPivot.getZ(),
                            
                           
                            Rotate.Y_AXIS );
                  
                        root.getTransforms().addAll( rotation);
                       
    
          
y=y+3;
if(y>363){
    y = 3;
}
                         
                }
                     if ( oldY < newY ) { 
                          if(x>-150){
                    rotation2 = new Rotate( -2,
                          
                            camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ(),
                            Rotate.X_AXIS );
                
                 camera.getTransforms().addAll( rotation2);
   circ1.getTransforms().addAll( rotation2);
x=x-2;


                          }
                          } else if ( oldY > newY ) { 
                                   if(x<50){
                    rotation2 = new Rotate( +2,camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ(),
                            Rotate.X_AXIS );
                     
x=x+2;
 
camera.getTransforms().addAll( rotation2);
   circ1.getTransforms().addAll( rotation2);

                                   }
                }
                  
                   oldY = newY;
                oldX = newX;    
                       
             if(!esc){
scene.setCursor(Cursor.NONE);
             }
             if(event.getScreenY()>screenHeight-10||event.getScreenX()>screenWidth-10||event.getScreenY()<10||event.getScreenX()<10){
                 
             
                
             }
            
           
              
            } else {
                
                   
               
                oldX = (int) event.getScreenX();
                oldY = (int) event.getScreenY();
                alreadyMoved = true;
                
            }
          
                
        }
    }
}