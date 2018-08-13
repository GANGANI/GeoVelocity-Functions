/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.identity.conditional.auth.functions.geovelocity;

public class CheckBrowserFunctionImpl implements CheckBrowserFunction{
    public String checkBrowser(String  browserDetails){

        String  user =   browserDetails.toLowerCase();
        String browser = "";

        //===============Browser===========================
        if(user.contains("msie"))

        {
            browser = "msie";
        } else if(user.contains("safari") && user.contains("version"))
        {
            browser = "safari";
        } else if (user.contains("opera"))
        {
            browser = "opera";
        } else if (user.contains("chrome"))
        {
            browser = "chrome";
        } else if (user.contains("firefox"))
        {
            browser= "firefox";
        } else if(user.contains("rv"))
        {
            browser="Internet Explore";
        } else
        {
            browser = "UnKnown";
        }
        return browser;
    }
}


