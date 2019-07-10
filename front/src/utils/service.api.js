import * as $ from 'jquery';

const url = "http://localhost:8080/api";
const contentType = "application/json; charset=utf-8";
const dataType = "json";

export function login(userId){
	return new Promise((res, rej) => {
		$.ajax({
			type: "GET",
			url: `${url}/users/${userId}`,
			contentType,
			dataType,
			success: (data) => {
				res(true)
			},
			failure: (errMsg) => {
				rej(errMsg)
			}
		});
	})
}

export function users() {
	return new Promise((res, rej) => {
		$.ajax({
			type: "GET",
			url: `${url}/users`,
			contentType,
			dataType,
			success: (data) => {
				res(data)
			},
			failure: (errMsg) => {
				rej(errMsg)
			}
		});
	})
}

export function subscribe(id, subscribeId){
	return new Promise((res, rej) => {
    $.ajax({
			type: "POST",
			url: `${url}/users/${id}/subscriber/${subscribeId}`,
			contentType,
			dataType,
			data: {},
			success: function (data) { 
				res(data); 
			},
			failure: function (errMsg) {
				rej(errMsg);
			}
		});
	});
}

