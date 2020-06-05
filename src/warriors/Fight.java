package warriors;
import warriors.ennemy.Ennemy;
import warriors.player.Player;

public class Fight {

	public Fight(MenuUI menu2) {
		this.menu = menu2;
		this.isCurrentFighting = false;
		this.fightStep = 0;
	}
	
	private MenuUI menu;
	private boolean isCurrentFighting;
	private Player playerFighting;
	private Ennemy ennemyFighting;
	private int fightStep;
	
	/**
	 * update Fight, return true is fight is over or  if there is no fight
	 * This is a step by step fight, where user can interact through menu
	 *
	 * @param void
	 * @return int life level
	 */
	public boolean updateFight() {
		boolean isFightingOver=true;
		if(this.isCurrentFighting) {
			
			switch (this.fightStep){
				
			
				case 0:
					int finalAttackPlayer = this.playerFighting.getFinalAttack();
					menu.printLine("Vous portez un coup à "+this.ennemyFighting.getName()+" avec une force de : "+finalAttackPlayer);
					this.ennemyFighting.reduceLife(finalAttackPlayer);
					if(ennemyFighting.isAlive()) {
						// Ennemy is still alive, fight is not over
						menu.printLine(this.ennemyFighting.getName()+" est toujours vivant, il lui reste :"+this.ennemyFighting.getLife()+" points de vie");
						isFightingOver = false;
					}else {
						menu.printLine("L'ennemi est vaincu : VICTOIRE");
						isFightingOver = true;
					}
					break;
				case 1:
					int attackEnnemy = this.ennemyFighting.getAttack();
					menu.printLine(this.ennemyFighting.getName()+" vous attaque avec une force de : "+attackEnnemy);
					this.playerFighting.reduceLife(attackEnnemy);
					if(this.playerFighting.isAlive()) {
						menu.printLine("Vous êtes encore vivant avec : "+this.playerFighting.getLife()+" points de vie");
					}else {
						menu.printLine(" AAARRGGGGH vous n'avez pas survecu à cette attaque fatale");
					}
					
					isFightingOver = true;
					break;
					
			
			
			}
			
			
		}
		if(isFightingOver) {
			this.closeFight();
		}else {
			this.fightStep++;
		}
		return this.isCurrentFighting;
	}
	
	private void closeFight() {
		this.isCurrentFighting = false;
	}
	
	public boolean getIsCurrentFighting() {

		return this.isCurrentFighting;
	}
	
	public void initFight(Player p, Ennemy e) {
		if(p !=null &&  p.isAlive() &&  e !=null && e.isAlive() ) {
			this.playerFighting = p;
			this.ennemyFighting = e;
			this.fightStep = 0;
			this.isCurrentFighting = true;
			menu.printLine("Vous venez de rencontrer :"+this.ennemyFighting.toString());
			menu.printLine("Le combat commence");
		}
		
	}
	
	
}
