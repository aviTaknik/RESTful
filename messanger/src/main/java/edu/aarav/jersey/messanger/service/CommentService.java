package edu.aarav.jersey.messanger.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.aarav.jersey.messanger.db.MyDataBase;
import edu.aarav.jersey.messanger.domain.Comment;
import edu.aarav.jersey.messanger.domain.Message;

public class CommentService {
	private Map<Long, Message> messages = null;

	public CommentService() {
		messages = MyDataBase.getMessages();

	}

	public List<Comment> getAllComments(long messageId) {
		Message message = messages.get(messageId);
		Map<Long, Comment> comments = message.getComments();
		List<Comment> values = (List<Comment>) comments.values();

		return values;

		// return new ArrayList<Comment>(messages.get(messageId).getComments().values());
	}

	public Comment getComment(long messageId, String commentId) {
		return messages.get(messageId).getComments().get(commentId);
	}

	public Comment makeComment(long messageId, Comment comment) {
		Message message = messages.get(messageId);
		Map<Long, Comment> commentMap = message.getComments();
		comment.setId(commentMap.size() + 1);
		comment.setCreated(new Date());
		commentMap.put(comment.getId(), comment);
		return commentMap.get(comment.getId());
	}

	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment deleteComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}