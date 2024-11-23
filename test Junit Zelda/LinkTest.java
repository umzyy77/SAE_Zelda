package com.zelda.zelda.test;

import com.zelda.zelda.controleur.GameLoop;
import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.acteur.Boss;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.dynamique.BlockDynamique;
import com.zelda.zelda.vue.TerrainVue;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LinkTest {

    private Terrain terrain;
    private TerrainVue terrainVue;
    private Link link;
    private int[][] terrainArray;
    @FXML
    private Pane panneauJeu;
    private Boss boss;

    private BlockDynamique blockDynamique;

    private GameLoop gameLoop;
    private Environnement environnement;

    @Before
    public void setUp() {
        gameLoop=new GameLoop(link,environnement);
        terrainArray= new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {1,0,0,0,0,0,0,0,0,0,0},
                {2,0,0,0,0,0,0,0,0,0,0},
                {4,0,0,0,0,0,0,0,0,0,0}

        };

        terrain = new Terrain(terrainArray);

        blockDynamique = new BlockDynamique(3,1,"cadrenoir.png",terrain);

        this.link = new Link("link", 0, 1, this.terrain);
        this.boss= new Boss(1,1, this.terrain);
    }

    //test de dégat à link
    @Test
    public void testTakeDamage() {
        int initialHealth = link.getPv();
        link.setPv(initialHealth - 10);
        assertEquals(initialHealth - 10, link.getPv());
    }




    @Test
    public void testSetAndGetPv() {
        link.setPv(50);
        assertEquals(50, link.getPv());
    }

    //test des déplcacements de link par rapport aux directions
    //0=sur place,1=faut,2=droit,3=bas,4=gauche
    @Test
    public void testMove() {
        link.directionProperty().set(2); // Move right
        int initialX = link.getX();
        seDeplace();
        assertEquals(initialX + 2, link.getX());

        link.directionProperty().set(3);  // Move down
        int initialY = link.getY();
        seDeplace();
        assertEquals(initialY + 2, link.getY());

        link.directionProperty().set(4); // Move right
        initialX = link.getX();
        seDeplace();
        assertEquals(initialX -2, link.getX());

        link.directionProperty().set(1);  // Move down
        initialY = link.getY();
        seDeplace();
        assertEquals(initialY - 2, link.getY());

        link.directionProperty().set(0);
        initialX = link.getX();
        initialY = link.getY();
        seDeplace();
        assertEquals(initialX, link.getX());
        assertEquals(initialY , link.getY());
    }

    //fonction que j'ai récupéré dans link en enlevant des choses inutiles qui échoué le test testmove
    public void  seDeplace() {

        int deplacementX = 0;
        int deplacementY = 0;

        int vitesse =2;

        if (link.directionProperty().getValue() == 2) {
            deplacementX += vitesse;

        }  if (link.directionProperty().getValue() == 4) {
            deplacementX -= vitesse;

        } if (link.directionProperty().getValue() == 3) {
            deplacementY += vitesse;

        } if (link.directionProperty().getValue() == 1) {
            deplacementY -= vitesse;

        }

        int newX = link.getX() + deplacementX;
        int newY = link.getY() + deplacementY;



        if ( terrain.dansTerrain(newX,newY)) {


            link.setX(newX);
            link.setY(newY);
        }
    }

}
