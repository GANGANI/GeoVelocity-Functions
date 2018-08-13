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

public class CheckOSFunctionImpl implements  CheckOSFunction {
    public String checkOS(String  userAgent){
        String os = "";

        String  user =   userAgent.toLowerCase();

        //===============OS===========================
        if (user.contains("linux"))
        {
            os = "linux";
        } else if (user.contains("windows") )
        {
            os = "windows";
        } else if (user.contains("unix"))
        {
            os = "unix";
        } else if (user.contains("mac os"))
        {
            os = "mac os";
        } else if (user.contains("solaris"))
        {
            os= "solaris";
        } else
        {
            os = "UnKnown";
        }
        return os;
    }
}
