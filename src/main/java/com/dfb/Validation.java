package com.dfb;




import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    JSONOperations jsonOperations = new JSONOperations("dataFields.json", "dataOut.json");

    public int check(MongoOperations mongoOperations, Map userData) throws IOException {

        Collection formFields1=userData.keySet();
        Collection formvalues=userData.values();



        System.out.println("formfields1 is " + formFields1.toArray()[0]);
        System.out.println("formValues is "+ formvalues.toArray()[0]);



        int i;
        String temp,maxi,input,value;
        Object objattr;
        JSONObject jobjattr;
        String formFields,patternstr;
        JSONObject attribute;
        JSONObject formStructure1;
        JSONOperations jops1 = new JSONOperations("dataFields.json", "dataOut.json");
        System.out.println("userdata is "+ userData);

        formStructure1 = mongoOperations.getFields();
        Set set =  formStructure1.keySet();
        Collection values1 =formStructure1.values();
       System.out.println("form structure is " + values1);

        Iterator iter = values1.iterator();


        while (iter.hasNext())
        {
            JSONParser parser = new JSONParser();
            Object obj;
            JSONObject jsonObj;
            String str = iter.next().toString();

            try {

                 obj = parser.parse(str);

                 jsonObj = (JSONObject) obj;
                System.out.println("json obj is" + jsonObj);

                Set set1 =jsonObj.keySet();

                Iterator iter1 = set1.iterator();

                while(iter.hasNext())
                {
                    formFields=iter1.next().toString();
                    temp=jsonObj.get(formFields).toString();

                    objattr=parser.parse(temp);
                    jobjattr=(JSONObject) objattr;

                    System.out.println("jobjattr is " + jobjattr);
                    if(jobjattr.containsKey("maxlength")) {

                        maxi = jobjattr.get("maxlength").toString();
                        float maxf= Float.parseFloat(maxi);


                      //  System.out.println("maxi is " + maxi);
                        i=0;
                        while(1>-1)
                        {
                            if(formFields1.toArray()[i].equals(formFields))
                            {
                                break;
                            }
                        }

                        value = formvalues.toArray()[i].toString();

                        if(value.length()>maxf)
                            System.out.println("Value in " + formFields+" is too large");

                    }

                    if(jobjattr.containsKey("pattern"))
                    {
                        patternstr = jobjattr.get("pattern").toString();
                        Pattern pattern = Pattern.compile(patternstr);
                        i=0;
                        while(1>-1)
                        {
                            if(formFields1.toArray()[i].equals(formFields))
                            {
                                break;
                            }
                        }
                        value = formvalues.toArray()[i].toString();
                        Matcher matcher = pattern.matcher(value);
                        boolean matchFound = matcher.matches();
                        if(!matchFound)
                            System.out.println("Value at " + formFields+ " does not obey the pattern");


                        

                    }

                }


            }
            catch(Exception e)
            {
                System.out.println("error is " + e.getMessage());
            }

       }



        return 1;
    }

}
