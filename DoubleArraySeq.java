//Mia Spiri
public class DoubleArraySeq implements Cloneable
{
    private double[] data;
    private int manyItems;
    private int currentIndex;
	
    public DoubleArraySeq()
    {
        manyItems = 0;
	data = new double[10];
    }
	
    public DoubleArraySeq(int initialCapacity)
    {
	if(initialCapacity>0){
	    data = new double[initialCapacity];
	}
	manyItems = 0;
	}
	
    public void addAfter (double element)
    {
	if ((manyItems+1) == data.length){
	    ensureCapacity((manyItems+1)*2);
	}
	if (isCurrent() && data[currentIndex]!=0){
	    for (int i = manyItems;i>currentIndex;i--)
	        data[i+1]=data[i];
		data[currentIndex+1]=element;
		currentIndex++;	
	    }
	    else {
	        data[currentIndex]=element;
		currentIndex=manyItems;
	    }
	 
	manyItems++;
    }
	
    public void addBefore (double element)
    {
	if ((manyItems+1) == data.length){
	    ensureCapacity((manyItems+1)*2);
	}
	if(isCurrent() && data[currentIndex-1]!=0){
	    for (int i=manyItems;i>currentIndex;i--)
		data[i]=data[i-1];
	}
	data[currentIndex] = element;
	manyItems++;
    }
	
    public void addAll(DoubleArraySeq addend)
    {
	ensureCapacity(manyItems + addend.manyItems);
	System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
	manyItems += addend.manyItems;
    }
	
	
	
    public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2)
    {
	s1.trimToSize();
	s2.trimToSize();
	DoubleArraySeq answer = new DoubleArraySeq(s1.getCapacity()+s2.getCapacity());
	System.arraycopy(s1.data, 0, answer.data, 0, s1.manyItems);
	System.arraycopy(s2.data, 0, answer.data, s1.manyItems, s2.manyItems);
	answer.manyItems = s1.manyItems + s2.manyItems;
	return answer;
    }
	
    public DoubleArraySeq clone( )
    {  // Clone a DoubleArraySeq object.
	DoubleArraySeq answer;
	      
	try
	{
	    answer = (DoubleArraySeq) super.clone( );
	}
	    catch (CloneNotSupportedException e)
	    {  // This exception should not occur. But if it does, it would probably
	         // indicate a programming error that made super.clone unavailable.
	         // The most common error would be forgetting the "Implements Cloneable"
	         // clause at the start of this class.
	    throw new RuntimeException
	        ("This class does not implement Cloneable");
	    }
	      
	    answer.data = data.clone( );
	      
	    return answer;
    }
	
	
    public void ensureCapacity(int minimumCapacity)
    {
	double[] biggerArray;
	    if (data.length < minimumCapacity){
		biggerArray = new double[minimumCapacity];
		System.arraycopy(data, 0, biggerArray, 0, manyItems);
		data = biggerArray;
	    }
    }
	
    public void removeCurrent()
    {
	if (isCurrent()){
	    for (int i = currentIndex;i<manyItems-1;i++)
	        data[i] = data [i+1];
		manyItems--;
	}
    }
	
    public int getCapacity()
    {
	return data.length;
    }
	
    public double getCurrent()
    {
	if(isCurrent()){
	    return data[currentIndex];
	}
	else{
	    throw new IllegalStateException("There is no current element");
	}
    }

    public void advance()
    {
	if (isCurrent())
	    currentIndex++;
    }

    public boolean isCurrent()
    {
	return (currentIndex != manyItems);
    }

    public int size()
    {
	return manyItems;
    }
	
    public void start()
    {
        if (isCurrent()){
	    currentIndex = 0;
	}
    }

    public void trimToSize( )
    {
        double[ ] trimmedArray;
	    if (data.length != manyItems)
	    {
	        trimmedArray = new double[manyItems];
	        System.arraycopy(data, 0, trimmedArray, 0, manyItems);
	        data = trimmedArray;
	    }
    }
	 
    public void print()
    {
	System.out.println("capacity = " + getCapacity());
	System.out.println("length = " + size());
	if (currentIndex!=0){ 
	    System.out.println("current element = " + getCurrent());
	}
	else{
	    System.out.println("There is no current element");
	}
	System.out.print("elements: ");
	for(int i=0; i<manyItems; i++)
	    System.out.print(data[i]+" ");
	System.out.println("\n");
	
    }
}
