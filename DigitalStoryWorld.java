import java.util.ArrayList;

public class DigitalStoryWorld {
	public static final String KEYWORD_IS = "is";
	
	public ArrayList<DigitalObject> objects;
	public ArrayList<DigitalObject> types;
	
	public DigitalStoryWorld(ArrayList<DigitalObject> objects, ArrayList<DigitalObject> types)
	{
		this.objects=objects;
		this.types=types;
	}
	
	public void cleanObjectTypes()
	{
		//this function cleans out duplicate affordances and changes the affordees to the objecttype.name instead of their object names
		for(int i=0;i<types.size(); ++i)
		{
			System.out.println("Cleaning.. type: " + types.get(i).name);
			DigitalObject type = types.get(i);
			for(int j=0;j<type.affordances.size();++j)
			{
				DigitalAffordance aff=type.affordances.get(j);
				for(int k =0;k< aff.instances.size();++k)
				{	// change all affordee names to their object type names if they have one
					if(aff.instances.get(k).affordee.ObjectType!=null)
					{
						//change
						aff.instances.get(k).affordee=aff.instances.get(k).affordee.ObjectType;
					}
				}
				//now that we have renamed affordees for all instances of the affordance, we remove the duplicate ones
				for(int k=0;k<aff.instances.size()-1;++k)
				{
					for(int l=1;l<aff.instances.size();++l)
					{
						if(aff.isIdentical(aff.instances.get(k),aff.instances.get(l)))
							aff.instances.remove(k);
					}
				}
			}
		}
	}
}
