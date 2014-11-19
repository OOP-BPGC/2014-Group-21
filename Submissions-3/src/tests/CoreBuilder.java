package tests;

import Base_Classes.Core;
import Base_Classes.Designation;

public class CoreBuilder extends PersonBuilder{
	public CoreBuilder() {};
	
	public final Designation desig = Designation.CORE;
	public int NumberOfMembers;
	
	public Core buildCore(){
		return (Core)(super.buildPerson());
	}

}
