package com.example

import io.grpc.Channel
import io.grpc.ManagedChannelBuilder

class DemoService {

	suspend fun saveUser() {
		val demoServerStub = createStub()

		val saveUserRequest = SaveUserRequest.newBuilder()
			.setName("Fernando")
			.setLastName("Queiroz")
			.setDocument("07284650714")
			.build()

		val saveUserResponse = demoServerStub.saveUser(saveUserRequest)

		println("Usuário registrado com id = " + saveUserResponse.id)
	}

	private fun createStub(): GrpcDemoServerServiceGrpcKt.GrpcDemoServerServiceCoroutineStub {
		val channel: Channel = ManagedChannelBuilder.forAddress("localhost", 50051)
			.usePlaintext()
			.build()

		return GrpcDemoServerServiceGrpcKt.GrpcDemoServerServiceCoroutineStub(channel)
	}
}
