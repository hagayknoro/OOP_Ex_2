# OOP_Ex_2   
### The project were written by:
- [Yehonatan Friedman](https://github.com/YehonatanFr)
- [Hagay Knorovich](https://github.com/hagayknoro)      

Ex2 in OOP course. The Ex have two parts.    
# Part 1   
In This part we want to create files and count the lines in multiple files and measure times in three different methods.     
## Thread & ThreadPool    
### Thread       
### ThreadPool    

## Method 1   
Method 1 implement in `getNumOfLines` function.    
In this function we move on all the files and we count the lines of each file.   
## Method 2    
This method is implement by Thread.    
Meaning, we write a `run` function (in `LineCounterThread` Class), and in this function we count the lines of file.    
In the main program we move on all the files in loop, and each file we run thread that his job is to count the lines, and than we merge together all the result.   
Fot this method we create a class name `LineCounterThread`, and we implement `run` function.   
## Methd 3    
This method we use ThreadPool.    
We implements `call` function in class we create. In the main we create ThreaPool for count lines of multiple files in parallel.     
For this method we create `LineCounterCallable` class, and implement `call` function.     
### We add UML Diagram For more explantion.       
![project_diagram](https://user-images.githubusercontent.com/118724971/211522147-8fd58dde-8589-4b13-a178-d5612d56f049.png)

## Measure Times     
For deep conclusion we measure time three times, in first time we create 100 files, second time we create 500 files and in the three time we create 1000 files.    
### 100 Files    
<img width="269" alt="image" src="https://user-images.githubusercontent.com/118724971/211595691-758af1ed-37f1-4515-be27-6c535a74c0f4.png">   

### 500 Files     
<img width="265" alt="image" src="https://user-images.githubusercontent.com/118724971/211595929-828fa0bf-a329-4e54-aa72-97e9b02a5444.png">       

### 1000 Files     
<img width="269" alt="image" src="https://user-images.githubusercontent.com/118724971/211596325-7333a927-e362-4c74-9223-c7407d7bde4d.png">     

## Conclusion     




