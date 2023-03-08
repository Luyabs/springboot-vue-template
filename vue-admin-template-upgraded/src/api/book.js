import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/vue-admin-template/table/list',
    method: 'get',
    params
  })
}

export function getPage(params) {
  return request({
    url: '/book',
    method: 'get',
    params
  })
}

export function getById(id) {
  return request({
    url: `/book/${id}`,
    method: 'get',
  })
}

export function addBook(data) {
  return request({
    url: '/book',
    method: 'post',
    data
  })
}

export function editBook(data) {
  return request({
    url: '/book/',
    method: 'put',
    data
  })
}

export function removeBook(id) {
  return request({
    url: `/book/${id}`,
    method: 'delete',
  })
}

export function sayHello() {
  return request({
    url: '/book/hello',
    method: 'post',
  })
}

