/*
 * Copyright IBM Corp. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package org.hyperledger.fabric.samples;

import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;

import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.identity.Identity;
import org.hyperledger.fabric.client.identity.Signer;
import org.hyperledger.fabric.client.identity.Signers;
import org.hyperledger.fabric.client.identity.X509Identity;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Hello world!
 *
 */
public class Sample 
{
	private static final X509Certificate certificate = null;
	private static final ECPrivateKey privateKey = null;
	private static final Identity identity = new X509Identity("MSP_ID", certificate);
    private static final Signer signer = Signers.newPrivateKeySigner((ECPrivateKey) privateKey);
    
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress("example.org", 1337).usePlaintext().build();
        
        Gateway gateway = Gateway.newInstance()
                .identity(identity)
                .signer(signer)
                .connection(channel)
                .connect();
        
        gateway.close();
    }
}
