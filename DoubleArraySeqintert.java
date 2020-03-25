public class DoubleArraySeq implements Cloneable
{
   private double[] data;
   private int manyItems;
   private int currentIndex; 
   
   
   public DoubleArraySeq( )
   {
      data = new double[10];
		manyItems = 0;
      currentIndex = 0;
   }
     

   public DoubleArraySeq(int cap)
   {
		if(cap > 0)
		{
   	   data = new double[cap];
			manyItems = 0;
         currentIndex = 0;
		}
		else
		{
			throw new IllegalArgumentException
			("The capacity cannot be negetive");
		}
   }
   

   private void ensureCapacity(int minimumCapacity)
   {
      double biggerArray[];
      if (data.length < minimumCapacity)
      {
         biggerArray = new double[minimumCapacity];
         System.arraycopy(data, 0, biggerArray, 0, manyItems);
         data = biggerArray;
      }
   }    
 
   
   public void addAfter(double element)
   {
		ensureCapacity((manyItems + 1)*2);
		for(int i = manyItems; i > currentIndex; i--)
			data[i] = data[i-1];
		data[currentIndex+1] = element;
		manyItems++;
      advance();
   }


   
   public void addBefore(double element)
   {
		ensureCapacity(manyItems + 1);
      for(int i = manyItems; i > currentIndex-1; i--)
			data[i] = data[i-1];
		data[currentIndex] = element;
		manyItems++;
   }
   
   
   
   public void addAll(DoubleArraySeq addend)
   {
      ensureCapacity(manyItems + addend.manyItems);

      System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
      manyItems += addend.manyItems;
      currentIndex = manyItems - 1;
   }   
   
   
   
   public void advance()
   {
      currentIndex++;
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
   

   
   public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2)
   {
      DoubleArraySeq s3 = new DoubleArraySeq(s1.manyItems + s2.manyItems);
		System.arraycopy(s1, 0, s3, 0, s1.manyItems);
		System.arraycopy(s2, 0, s3, s1.manyItems, s2.manyItems);
		s3.manyItems = (s1.manyItems + s2.manyItems);
		return s3;		
   }
   
   
   public int getCapacity()
   {
		int cap = data.length;
      return cap;
   }

   public boolean isCurrent()
   {
   
      boolean answer = false;
      
   	if(currentIndex <= data.length)
			answer = true;
                 
      return answer;
      
   }

   public double getCurrent( )
   {
		if(isCurrent() == true)
      	return data[currentIndex];
		else
			throw new IllegalStateException
			("There is no current element");
   }
              
   
   public void removeCurrent( )
   {
		if(currentIndex != manyItems)
		{
            int i;
				for(i = currentIndex; i<manyItems-1; i++)
				{
					data[i] = data[i+1];
				}
				manyItems--;
		}
		else
		{
			throw new IllegalStateException
			("There is no current element");
		}
   }                 
   

   public int size( )
   {
      return manyItems;
   }   
   
   
   public void start( )
   {
		if(data[0] != 0)
      	currentIndex = 0;
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
	System.out.println("current element = " + currentIndex);
	System.out.println("elements: ");
      
      for(int i = 0; i < manyItems; i++)
      {
      
         double answer = data[i];
         System.out.println(answer);
      
      }
      
   }
}