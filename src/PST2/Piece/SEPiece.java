package PST2.Piece;

import PST2.Game;
import PST2.IO.Read;
import PST2.StratEdge;
import static PST2.Game.C;

public class SEPiece implements Piece
{
    /*Variables static*/
    private static int[][] TABMOVES;                                            //Données contenues dans MOVESFILE
    private static int[][] TABUMOVES;                                           //Données contenues dans UMOVESFILE
    private static int[][] TABPIECES;                                           //Données contenues dans PIECESFILE
    private static String[] TABNAMES;                                           //Données contenues dans NAMESFILE
    
    /*Constantes non-static*/
    private final String NAME;                                                  //Nom de la pièce
    protected int[] moves;                                                      //Mouvements potentiellement autorisés (dépend de type)
    
    /*Variables non-static*/
    private int type;                                                           //Type de pièce : Roi:0; Reine:1; Fou:2; Chevalier:3; Tour:4; Pion:5.
    private boolean team;                                                       //Equipe à laquelle appartient la pièce : false équipe du haut
    private int image;                                                          //Index de l'image permettant de représenter la pièce sur le plateau de jeu
    private int attack;                                                         //Attaque de la pièce (en nombre de pdv enlevé)
    private int defense;                                                        //Defense de la pièce
    private int life;                                                           //Nombre de pdv de la pièce
    private int x;                                                              //Coordonnées x de la pièce (en cellule)
    private int y;                                                              //Coordonnées y de la pièce (en cellule)
    private boolean alive;                                                      //Booléen qui indique si la pièce est en vie
    protected boolean firstMove = false;                                        //Détermine si le premier mouvement a été effectué
    //protected boolean[][] pMoves = new boolean[C][C];                           //Mouvements possibles de la pièce
    
    /*Constructeur*/
    public SEPiece(String NAME, int type, boolean team, int image, int attack, int defense, int life, int x, int y)
    {
        this.NAME = NAME;
        this.type = type;
        this.team = team;
        this.image = image;
        this.attack = attack;
        this.defense = defense;
        this.life = life;
        this.x = x;
        this.y = y;
        alive = true;
        moves = TABMOVES[type].clone();
    }
    
    public SEPiece(String NAME, int type, boolean team, int image, int x, int y)//Pièce classique
    {
        this(NAME, type, team, image, 1, 0, 1, x, y);
    }
    
    /*Méthodes*/
    
    /**Charge les pièces et leurs mouvements
     * @param r L'objet Read qui nous permet de lire.*/
    public static void load(Read r)
    {
        TABMOVES = r.matrixTextFile(MOVESFILE);
        TABUMOVES = r.matrixTextFile(UMOVESFILE);
        TABPIECES = r.matrixTextFile(PIECESFILE);
        TABNAMES = r.file(NAMESFILE);
    }
    
    @Override
    public void testDirection(int dir, int dist, int nUM, Piece[][] checker, boolean[][] pMoves)
    {
        if(nUM == 0)return;
        int xm = x + TABUMOVES[dir][0] * (dist-nUM+1);
        int ym = y + TABUMOVES[dir][1] * (dist-nUM+1);
        if(xm >= 0 && xm < C && ym >= 0 && ym < C)
        {
            if(checker[ym][xm] == null)
            {
                pMoves[ym][xm] = true;
                testDirection(dir, dist, nUM-1, checker, pMoves);
            }
            else
            {
                pMoves[ym][xm] = checker[ym][xm].getTeam() != team;
                testDirection(dir, dist, 0, checker, pMoves);
            }
        }
    }
    
    @Override
    public void saveTheKing(boolean[][] pMoves)
    {
        Game g = StratEdge.getSE().getGame();
        Piece[][] fChecker;
        //Piece fPiece = clone();
        int saveX = x;
        int saveY = y;
        boolean saveFM = firstMove;
        for(int i = 0; i < pMoves.length; i++)                                  
            for(int j = 0; j < pMoves[i].length; j++)
                if(pMoves[i][j])
                {
                    fChecker = g.cloneChecker();                                //Clonage du checker <=> fChecker est virtuel
                    move(j, i, fChecker);
                    if(g.isCheck(team, fChecker))
                        pMoves[i][j] = false;
                }
        setPos(saveX, saveY);
        firstMove = saveFM;
    }
    
    @Override
    public void move(int x,int y, Piece[][] checker)
    {
        checker[getY()][getX()] = null;                                         //On supprime la pièce de la case où elle se trouve
        if(checker[y][x]!=null)
        {
            Piece piece = checker[y][x];
            life-=piece.getAtt();
            piece.setLife(piece.getLife()-attack);
            if(life>0)
            {
                setPos(x, y);                                                   //On modifie ses coordonnées
                checker[getY()][getX()] = this;                                 //On déplace la pièce sélectionnée sur la case sélectionnée
            }
            else if(piece.getLife()<=0)
                checker[y][x]=null;
        }
        else{
            setPos(x, y);                                                       //On modifie ses coordonnées
            checker[getY()][getX()] = this;                                     //On déplace la pièce sélectionnée sur la case sélectionnée
        }
    }

    /*@Override
    public void move(int x, int y, Piece[][] checker)
    {
        checker[getY()][getX()] = null;                                         //On supprime la pièce de la case où elle se trouve
        setPos(x, y);                                                           //On modifie ses coordonnées
        checker[getY()][getX()] = this;                                         //On déplace la pièce sélectionnée sur la case sélectionnée
    }*/
    
    /*Getters*/
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    @Override
    public String getName(){return NAME;}
    @Override
    public int getType(){return type;}
    @Override
    public boolean getTeam(){return team;}
    @Override
    public int getImg(){return image;}
    @Override
    public int getAtt(){return attack;}
    @Override
    public int getDef(){return defense;}
    @Override
    public int getLife(){return life;}
    @Override
    public int getX(){return x;}
    @Override
    public int getY(){return y;}
    @Override
    public boolean isAlive(){return alive;}
    @Override
    public boolean getFM(){return firstMove;}
    @Override
    public boolean[][] getMoves(Piece[][] checker, boolean saveTheKing)
    {
        boolean[][] pMoves = new boolean[C][C];
        for(int dir = 0; dir < moves.length; dir++)
            testDirection(dir, moves[dir], moves[dir], checker, pMoves);
        if(saveTheKing)
            saveTheKing(pMoves);
        return pMoves;
    }
    
    public static int[][] getPieces(){return TABPIECES;}
    
    public static String[] getNames(){return TABNAMES;}
    
    /*Setters*/
    
    @Override
    public void setType(int nType){type = nType;}
    @Override
    public void setTeam(boolean nTeam){team = nTeam;}
    @Override
    public void setImg(int nImg){image = nImg;}
    @Override
    public void setAtt(int nAtt){attack = nAtt;}
    @Override
    public void setDef(int nDef){defense = nDef;}
    @Override
    public void setLife(int nLife){life = nLife;}
    @Override
    public void setPos(int nX, int nY)
    {
        x = nX; 
        y = nY;
        if(nX != 0 || nY != 0)
            firstMove = true;                                                   //Permet de savoir si le pion s'est déjà déplacé
    }
    
    @Override
    public void kill(){alive = false;}
}